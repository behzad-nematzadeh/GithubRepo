package ir.behzadnematzadeh.githubrepo.util

class Resource<out T> private constructor(
    private val data: T?,
    private val error: Throwable?,
) {

    val isSuccessful: Boolean
        get() = data != null && error == null

    constructor(data: T) : this(data, null)

    constructor(exception: Throwable) : this(null, exception)

    fun data(): T {
        if (error != null) {
            throw IllegalStateException("Check isSuccessful first: call error() instead.")
        }
        return data!!
    }

    fun error(): Throwable {
        if (data != null) {
            throw IllegalStateException("Check isSuccessful first: call data() instead.")
        }
        return error!!
    }
}