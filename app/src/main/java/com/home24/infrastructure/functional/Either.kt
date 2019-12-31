package com.home24.infrastructure.functional

//Please note that the either class itself doesn't hold any values. Its child classes does.
sealed class Either<out L, out R> {
    // by FP conventions left would be the failure of this Either
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    // by FP conventions right would be the Success of this Either
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }

}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }

fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.c(::right))

