package mx.com.test.android.domain.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import mx.com.test.android.domain.common.result.Result

abstract class CoroutineUseCase<in P, out R>(
    private val exceptionHandler: ExceptionHandler,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(param: P): Result<R> {
        return try {
            withContext(dispatcher) {
                execute(param)
            }
        } catch (throwable: Throwable) {
            exceptionHandler.handle(throwable)
            Result.Failure(throwable)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(param: P): Result<R>

}