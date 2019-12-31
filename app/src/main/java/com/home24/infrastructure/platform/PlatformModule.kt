package com.mobiquity.infrastructure.platform

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.dsl.module

val platformModule = module {
    single { CoroutineScope(Dispatchers.IO + Job()) }
}