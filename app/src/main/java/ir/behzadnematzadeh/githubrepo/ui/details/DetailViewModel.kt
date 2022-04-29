package ir.behzadnematzadeh.githubrepo.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.behzadnematzadeh.githubrepo.remote.repository.UserRemoteRepository
import javax.inject.Inject
import javax.inject.Provider

class DetailViewModel @Inject constructor(
    remoteRepository: UserRemoteRepository
) : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    class Factory @Inject constructor(
        private val provider: Provider<DetailViewModel>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = provider.get() as T
    }

    private val tag = "MainViewModel"

    init {
        remoteRepository.userRepository("JakeWharton")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(tag, "$it")
                },
                {
                    Log.e(tag, it.toString())
                }
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