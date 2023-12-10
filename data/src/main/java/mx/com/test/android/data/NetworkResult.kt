package mx.com.test.android.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

sealed class NetworkResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : NetworkResult<T>()

    data class Error(val code: Int, val message: String) : NetworkResult<Nothing>()

    data class Failure(val exception: Exception) : NetworkResult<Nothing>()

    override fun toString(): String {
        val value = when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[code=$code, message=$message]"
            is Failure -> "Failure[exception=$exception]"
        }
        return value
    }
}

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = apiCall.invoke()
            if (response.isSuccessful && response.body() != null) {
                return@withContext NetworkResult.Success(response.body()!!)
            } else {
                return@withContext NetworkResult.Error(response.code(), response.message())
            }
        } catch (throwable: Throwable) {
            return@withContext NetworkResult.Failure(throwable as Exception)
        }
    }
}

suspend fun doNetworkCall() {

}

suspend inline fun <T> doNetworkCall(
    crossinline body: suspend () -> T
): Result<T> {
    return try {
        val response = withContext(Dispatchers.IO) {
            body()
        }
        Result.success(response)
    } catch (ex: Exception) {
        return Result.failure(ex)
    }

}