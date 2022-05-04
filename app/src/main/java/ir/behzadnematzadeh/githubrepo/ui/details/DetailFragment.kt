package ir.behzadnematzadeh.githubrepo.ui.details

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ir.behzadnematzadeh.githubrepo.BaseApp
import ir.behzadnematzadeh.githubrepo.R
import ir.behzadnematzadeh.githubrepo.databinding.FragmentDetailsBinding
import ir.behzadnematzadeh.githubrepo.databinding.FragmentMainBinding
import javax.inject.Inject

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = requireNotNull(_binding) { "You can't touch binding when it's null" }

    @Inject
    lateinit var viewModelFactory: DetailViewModel.Factory
    private lateinit var viewModel: DetailViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        BaseApp.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailsBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }
}