package ir.behzadnematzadeh.githubrepo.util

sealed interface ViewResource<T> {
    class NotInitialized<T> : ViewResource<T>
    class Loading<T> : ViewResource<T>
    data class Success<T>(val data: T) : ViewResource<T>
    data class Failure<T>(val error: Throwable) : ViewResource<T>
}