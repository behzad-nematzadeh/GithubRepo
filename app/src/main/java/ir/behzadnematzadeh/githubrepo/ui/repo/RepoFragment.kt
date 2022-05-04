package ir.behzadnematzadeh.githubrepo.ui.repo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ir.behzadnematzadeh.githubrepo.BaseApp
import ir.behzadnematzadeh.githubrepo.databinding.FragmentRepoBinding
import javax.inject.Inject

class RepoFragment : Fragment() {

    private var _binding: FragmentRepoBinding? = null
    private val binding: FragmentRepoBinding
        get() = requireNotNull(_binding) { "You can't touch binding when it's null" }

    @Inject
    lateinit var viewModelFactory: RepoViewModel.Factory
    private lateinit var viewModel: RepoViewModel

    private var adapter: RepoAdapter? = null

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

        loadRepo()

        adapter = RepoAdapter {
            val direction = RepoFragmentDirections.toDetailFragment(it)
            findNavController().navigate(direction)
        }
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.btnLoad.setOnClickListener {
            loadRepo()
        }

        viewModel.repos.observe(viewLifecycleOwner) { event ->
            binding.progress.visibility = View.INVISIBLE
            binding.btnLoad.isEnabled = true
            if (event.isSuccessful) {
                adapter?.addAll(event.data())
            }
        }
    }

    private fun loadRepo() {
        with(binding) {
            recycler.adapter?.let { recyclerViewAdapter ->
                recyclerViewAdapter.notifyItemRangeRemoved(
                    0,
                    recyclerViewAdapter.itemCount
                )
            }
            progress.visibility = View.VISIBLE
            btnLoad.isEnabled = false
        }
        viewModel.loadResults()
    }
}