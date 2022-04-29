package ir.behzadnematzadeh.githubrepo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ir.behzadnematzadeh.githubrepo.BaseApp
import ir.behzadnematzadeh.githubrepo.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModel.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BaseApp.component.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
}