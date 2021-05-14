package com.app.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.app.demo.adapter.StoreListAdapter
import com.app.demo.component.ItemOffsetDecoration
import com.app.demo.data.StoreDataResponseItem
import com.app.demo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_store.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StoreFragment : Fragment() {

    private val userDetailsViewModel: UserViewModel by sharedViewModel()
    private lateinit var mActivity: MainActivity

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainActivity
        fetchStoreData()
    }

    private fun fetchStoreData() {
        userDetailsViewModel.userList.observe(viewLifecycleOwner, Observer {
            it?.let {
                val adapter = StoreListAdapter(mActivity,it)
                val itemDecoration = ItemOffsetDecoration(mActivity, R.dimen.item_offset)
                rvList.addItemDecoration(itemDecoration)
                rvList.apply {
                    layoutManager = LinearLayoutManager(mActivity)
                }
                rvList.adapter = adapter

                adapter.setOnItemClickListener(object : StoreListAdapter.OnItemClickListener {
                    override fun onClick(view: View, data: StoreDataResponseItem)
                    {
                        userDetailsViewModel.updateStoreItemData(data)
                        findNavController().navigate(R.id.action_StoreFragment_to_DetailsFragment)
                    }
                })
            }
        })
    }



}


