package ir.behzadnematzadeh.githubrepo.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.behzadnematzadeh.githubrepo.model.UserRepo
import ir.behzadnematzadeh.githubrepo.remote.repository.UserRemoteRepository
import ir.behzadnematzadeh.githubrepo.util.Resource
import javax.inject.Inject
import javax.inject.Provider

class RepoViewModel @Inject constructor(
    private val remoteRepository: UserRemoteRepository
) : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    class Factory @Inject constructor(
        private val provider: Provider<RepoViewModel>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = provider.get() as T
    }

    private val tag = "MainViewModel"

    private val _repos = MutableLiveData<Resource<List<UserRepo>>>()
    val repos: LiveData<Resource<List<UserRepo>>> get() = _repos

    fun loadResults() {
        remoteRepository.userRepository("JakeWharton")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { userRepoList -> _repos.value = Resource(userRepoList) },
                { error -> _repos.value = Resource(error) }
            ).addToComposite()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun Disposable.addToComposite() {
        compositeDisposable.add(this)
    }
}