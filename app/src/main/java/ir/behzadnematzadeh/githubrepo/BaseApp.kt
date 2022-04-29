package ir.behzadnematzadeh.githubrepo

import android.app.Application
import ir.behzadnematzadeh.githubrepo.di.component.DaggerGithubRepoComponent
import ir.behzadnematzadeh.githubrepo.di.component.GithubRepoComponent

class BaseApp : Application() {
    companion object {
        val component: GithubRepoComponent by lazy {
            DaggerGithubRepoComponent
                .factory()
                .create()
        }
    }
}