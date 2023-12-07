package mx.com.test.android.domain.common

fun interface ExceptionHandler {
    fun handle(t: Throwable)
}