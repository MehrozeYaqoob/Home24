package com.home24.infrastructure.extensions

import com.home24.infrastructure.exception.Failure
import com.home24.infrastructure.functional.Either
import retrofit2.Call
import java.net.UnknownHostException


/**
 * Takes in a transform lambda to return a modified version of the response
 */
fun <T, R> Call<T>.requestTransformBlocking(transform: (T) -> R): Either<Failure, R> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform(response.body()!!))
            false -> Either.Left(Failure.ServerError)
            }
    } catch (exception: Throwable) {
        when (exception) {
            is UnknownHostException -> Either.Left(Failure.NetworkConnection)
            else -> Either.Left(Failure.ServerError)
        }
    }
}

fun <T> Call<T>.requestBlocking(default: T): Either<Failure, T> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right((response.body() ?: default))
            false -> Either.Left(Failure.ServerError)
        }
    } catch (exception: Throwable) {
        when (exception) {
            is UnknownHostException -> Either.Left(Failure.NetworkConnection)
            else -> Either.Left(Failure.ServerError)
        }
    }
}

fun <T> Call<T>.requestBlocking(): Either<Failure, T> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right((response.body()!!))
            false -> Either.Left(Failure.ServerError)
            }
    } catch (exception: Throwable) {
        when (exception) {
            is UnknownHostException -> Either.Left(Failure.NetworkConnection)
            else -> Either.Left(Failure.ServerError)
        }
    }
}





