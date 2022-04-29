package ir.behzadnematzadeh.githubrepo.di.component

import dagger.Component
import ir.behzadnematzadeh.githubrepo.di.module.NetModule
import ir.behzadnematzadeh.githubrepo.di.module.UserRemoteModule
import ir.behzadnematzadeh.githubrepo.remote.repository.UserRemoteRepository
import ir.behzadnematzadeh.githubrepo.ui.details.DetailFragment
import ir.behzadnematzadeh.githubrepo.ui.main.MainFragment

@Component(
    modules = [
        NetModule::class,
        UserRemoteModule::class,
    ]
)
interface GithubRepoComponent {
    fun inject(mainFragment: MainFragment)
    fun inject(detailFragment: DetailFragment)

    @Component.Factory
    interface Factory {
        fun create(): GithubRepoComponent
    }

    fun userRemoteRepository(): UserRemoteRepository
}