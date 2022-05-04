package ir.behzadnematzadeh.githubrepo.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ir.behzadnematzadeh.githubrepo.BaseApp
import ir.behzadnematzadeh.githubrepo.databinding.FragmentMainBinding
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = requireNotNull(_binding) { "You can't touch binding when it's null" }

    @Inject
    lateinit var viewModelFactory: MainViewModel.Factory
    private lateinit var viewModel: MainViewModel

    private var mainAdapter: MainAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        BaseApp.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainAdapter = MainAdapter { }
        binding.recycler.adapter = mainAdapter
        binding.recycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.btnLoad.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            it.isEnabled = false
            viewModel.loadResults()
        }

        viewModel.repos.observe(viewLifecycleOwner) { event ->
            binding.progress.visibility = View.INVISIBLE
            binding.btnLoad.isEnabled = true
            if (event.isSuccessful) {
                mainAdapter?.addAll(event.data())
            }
            //findNavController().navigate(R.id.to_detailFragment)
        }
    }
}