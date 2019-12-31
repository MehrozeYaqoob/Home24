package com.home24.infrastructure.android

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class Home24Coroutine : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    val mainDispatcher : CoroutineContext by lazy { Dispatchers.Main }
}

interface Home24Coroutine_ {
    fun ioDispatcher() : CoroutineContext
    fun mainDispatcher() : CoroutineContext
}

class Home24Coroutine__: Home24Coroutine_ {
    private val job = Job()
    override fun ioDispatcher(): CoroutineContext {
        return Dispatchers.IO + job
    }

    override fun mainDispatcher(): CoroutineContext {
        return Dispatchers.Main + job
    }

}

class TestHome24CoroutineCoroutine__: Home24Coroutine_ {
    override fun ioDispatcher(): CoroutineContext {
        return Dispatchers.Unconfined
    }

    override fun mainDispatcher(): CoroutineContext {
        return Dispatchers.Unconfined
    }

}