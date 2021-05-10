package com.app.demo.usecase

interface UseCase<out R> {
    fun execute(): R
}

interface Cancellable {
    fun cancel()
}