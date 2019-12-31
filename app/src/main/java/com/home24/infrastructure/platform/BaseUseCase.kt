package com.home24.infrastructure.platform

import com.home24.infrastructure.exception.Failure
import com.home24.infrastructure.functional.Either
import kotlinx.coroutines.*

abstract class BaseUseCase<out Type, in Params>(private val ioScope: CoroutineScope, val main : CoroutineDispatcher = Dispatchers.Main) where Type : Any? {
    abstract suspend fun run(param: Params): Either<Failure, Type>
    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit) {
        ioScope.launch {
            val result = run(params)
            withContext(main)
            {
                onResult(result)
            }
        }
    }

    class None
}