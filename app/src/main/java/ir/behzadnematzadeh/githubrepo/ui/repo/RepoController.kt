package ir.behzadnematzadeh.githubrepo.ui.repo

import com.airbnb.epoxy.TypedEpoxyController
import ir.behzadnematzadeh.githubrepo.model.UserRepo

class RepoController(
    private val listener: ClickListener
) : TypedEpoxyController<List<UserRepo>>() {

    override fun buildModels(userRepoList: List<UserRepo>) {
        userRepoList.forEachIndexed { _, userRepo ->
            RepoItemViewModel_()
                .id(userRepo.id)
                .repoName(userRepo.name)
                .cardClick { _, _, _, position ->
                    listener.onItemClicked(userRepo, position)
                }
                .addTo(this)
        }
    }
}