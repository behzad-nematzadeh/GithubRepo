package ir.behzadnematzadeh.githubrepo.di.module

import dagger.Module
import dagger.Provides
import ir.behzadnematzadeh.githubrepo.remote.repository.UserRemoteRepository
import ir.behzadnematzadeh.githubrepo.remote.repository.UserRemoteRepositoryImpl

@Module
class UserRemoteModule {
    @Provides
    fun provideUserRemote(userRemoteRepositoryImpl: UserRemoteRepositoryImpl): UserRemoteRepository {
        return userRemoteRepositoryImpl
    }
}