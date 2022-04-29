package ir.behzadnematzadeh.githubrepo.remote.services

import io.reactivex.Observable
import ir.behzadnematzadeh.githubrepo.model.UserRepo
import retrofit2.http.GET
import retrofit2.http.Path

const val GITHUB_BASE_URL: String = "https://api.github.com/"

interface ApiService {
    @GET("/users/{user}/repos")
    fun fetchUserRepository(@Path("user") user: String): Observable<List<UserRepo>>
}