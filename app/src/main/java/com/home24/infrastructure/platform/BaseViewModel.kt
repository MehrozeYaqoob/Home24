package com.home24.infrastructure.platform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.home24.infrastructure.android.SingleLiveEvent
import com.home24.infrastructure.exception.Failure

abstract class BaseViewModel(androidApplication: Application) : AndroidViewModel(androidApplication)
{
    var failure: SingleLiveEvent<Failure> = SingleLiveEvent() // the failure should just emit once. We don't want failures to emit when an observer resubscribes to this property.

    fun handleFailure(failure: Failure)
    {
        this.failure.value=failure
    }
}