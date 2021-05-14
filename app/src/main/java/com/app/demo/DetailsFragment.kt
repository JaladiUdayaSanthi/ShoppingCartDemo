package com.app.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.demo.viewmodel.UserViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFragment : Fragment() {

    private val userDetailsViewModel: UserViewModel by sharedViewModel()
    private lateinit var mActivity: MainActivity

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainActivity
        val data  = userDetailsViewModel.getStoreItemData()

        data.let {
            Glide.with(mActivity)
                    .load(it.image)
                    .placeholder(R.drawable.default_image)
                    .error(R.drawable.image_not_found)
                    .into(itemImage)
            itemName.text = "Item Name: "+it.title
            itemCategory.text = "Item Category: "+it.category
            itemPrice.text = "Item Price: "+it.price.toString()
            itemDescription.text = "Item Description: "+it.description

        }

        addtoCart.setOnClickListener {
            userDetailsViewModel.addItemToCart(data)
            findNavController().navigate(R.id.action_DetailsFragment_to_StoreFragment)
        }

    }
}