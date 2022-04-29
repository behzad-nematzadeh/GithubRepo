package ir.behzadnematzadeh.githubrepo.remote.repository

import io.reactivex.Observable
import ir.behzadnematzadeh.githubrepo.model.UserRepo
import ir.behzadnematzadeh.githubrepo.remote.services.ApiService
import javax.inject.Inject

class UserRemoteRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    UserRemoteRepository {
    override fun userRepository(userName: String): Observable<List<UserRepo>> {
        return apiService.fetchUserRepository(userName)
    }
}