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

class RepoFragment : Fragment(), RepoController.ClickListener {

    private var _binding: FragmentRepoBinding? = null
    private val binding: FragmentRepoBinding
        get() = requireNotNull(_binding) { "You can't touch binding when it's null" }

    @Inject
    lateinit var viewModelFactory: RepoViewModel.Factory
    private lateinit var viewModel: RepoViewModel

    private lateinit var controller: RepoController

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

        binding.btnLoad.setOnClickListener {
            viewModel.loadResults()
        }

        viewModel.repos.observe(viewLifecycleOwner) { event ->
            when (event) {
                is ViewResource.NotInitialized -> {
                    binding.btnLoad.isEnabled = false
                }
                is ViewResource.Loading -> {
                    binding.btnLoad.isEnabled = false
                }
                is ViewResource.Success -> {
                    binding.btnLoad.isEnabled = true
                }
                is ViewResource.Failure -> {
                    binding.btnLoad.isEnabled = true
                }
            }
            controller.setData(event)
        }
    }

    override fun onItemClicked(userRepo: UserRepo, position: Int) =
        findNavController().navigate(RepoFragmentDirections.toDetailFragment(userRepo))
}