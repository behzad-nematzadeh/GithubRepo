package ir.behzadnematzadeh.githubrepo.util

sealed interface ViewResource<T> {
    object NotInitialized : ViewResource<Any>
    object Loading : ViewResource<Any>
    data class Success<T>(val data: T) : ViewResource<T>
    data class Failure<T>(val error: Throwable) : ViewResource<T>
}