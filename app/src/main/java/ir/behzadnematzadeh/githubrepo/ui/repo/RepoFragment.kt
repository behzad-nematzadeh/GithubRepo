package ir.behzadnematzadeh.githubrepo.ui.repo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ir.behzadnematzadeh.githubrepo.BaseApp
import ir.behzadnematzadeh.githubrepo.databinding.FragmentRepoBinding
import ir.behzadnematzadeh.githubrepo.model.UserRepo
import ir.behzadnematzadeh.githubrepo.util.ViewResource
import javax.inject.Inject

class RepoFragment : Fragment(), ClickListener {

    private var _binding: FragmentRepoBinding? = null
    private val binding: FragmentRepoBinding
        get() = requireNotNull(_binding) { "You can't touch binding when it's null" }

    @Inject
    lateinit var viewModelFactory: RepoViewModel.Factory
    private lateinit var viewModel: RepoViewModel

    lateinit var controller: RepoController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        BaseApp.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[RepoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRepoBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller = RepoController(this)
        binding.recycler.setController(controller)

        loadRepo()
        binding.btnLoad.setOnClickListener {
            loadRepo()
        }

        viewModel.repos.observe(viewLifecycleOwner) { event ->
            binding.progress.visibility = View.INVISIBLE
            binding.btnLoad.isEnabled = true
            when (event) {
                is ViewResource.Failure -> {}
                is ViewResource.Success -> controller.setData(event.data)
                else -> return@observe
            }
        }
    }

    private fun loadRepo() {
        with(binding) {
            progress.visibility = View.VISIBLE
            btnLoad.isEnabled = false
        }
        viewModel.loadResults()
    }

    override fun onItemClicked(userRepo: UserRepo, position: Int) {
        val direction = RepoFragmentDirections.toDetailFragment(userRepo)
        findNavController().navigate(direction)
    }
}

interface ClickListener {
    fun onItemClicked(userRepo: UserRepo, position : Int)
}