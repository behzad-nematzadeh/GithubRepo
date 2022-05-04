package ir.behzadnematzadeh.githubrepo.di.component

import dagger.Component
import ir.behzadnematzadeh.githubrepo.di.module.NetModule
import ir.behzadnematzadeh.githubrepo.di.module.UserRemoteModule
import ir.behzadnematzadeh.githubrepo.remote.repository.UserRemoteRepository
import ir.behzadnematzadeh.githubrepo.ui.details.DetailFragment
import ir.behzadnematzadeh.githubrepo.ui.repo.RepoFragment

@Component(
    modules = [
        NetModule::class,
        UserRemoteModule::class,
    ]
)
interface GithubRepoComponent {
    fun inject(repoFragment: RepoFragment)
    fun inject(detailFragment: DetailFragment)

    @Component.Factory
    interface Factory {
        fun create(): GithubRepoComponent
    }

    fun userRemoteRepository(): UserRemoteRepository
}