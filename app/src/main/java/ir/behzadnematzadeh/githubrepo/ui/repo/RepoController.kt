package ir.behzadnematzadeh.githubrepo.ui.repo

import com.airbnb.epoxy.TypedEpoxyController
import ir.behzadnematzadeh.githubrepo.model.UserRepo
import ir.behzadnematzadeh.githubrepo.util.ViewResource

class RepoController(
    private val listener: ClickListener
) : TypedEpoxyController<ViewResource<List<UserRepo>>>() {

    override fun buildModels(resource: ViewResource<List<UserRepo>>?) {
        when (resource) {
            is ViewResource.NotInitialized -> {

            }
            is ViewResource.Loading -> {
                repoLoadingView {
                    id(0)
                    fullScreen(true)
                }
            }
            is ViewResource.Success -> {
                if (resource.data.isEmpty()) {

                } else {
                    resource.data.forEachIndexed { _, userRepo ->
                        repoItemView {
                            id(userRepo.id)
                            repoName(userRepo.name)
                            cardClick { _, _, _, position ->
                                this@RepoController.listener.onItemClicked(userRepo, position)
                            }
                        }
                    }
                }
            }
            is ViewResource.Failure -> {

            }
            else -> {}
        }
    }

    interface ClickListener {
        fun onItemClicked(userRepo: UserRepo, position: Int)
    }
}