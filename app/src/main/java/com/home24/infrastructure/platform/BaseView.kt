package com.home24.infrastructure.platform

import android.os.Bundle

interface BaseView {
    fun showMessage(message:String)
    fun ignite(bundle:Bundle?)
}