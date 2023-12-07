package mx.com.test.android.domain.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import mx.com.test.android.domain.common.result.Result

abstract class FlowUseCase<in P, out R>(
    private val exceptionHandler: ExceptionHandler,
    private val dispatcher: CoroutineDispatcher,
) {
    operator fun invoke(params: P): Flow<Result<R>> {
        return execute(params).catch { throwable ->
            exceptionHandler.handle(throwable)
            emit(Result.Failure(throwable))
        }.flowOn(dispatcher)
    }

    protected abstract fun execute(params: P): Flow<Result<R>>
}