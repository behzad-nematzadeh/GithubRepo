package ir.behzadnematzadeh.githubrepo.remote.repository

import io.reactivex.Observable
import ir.behzadnematzadeh.githubrepo.model.UserRepo

interface UserRemoteRepository {
    fun userRepository(userName: String): Observable<List<UserRepo>>
}