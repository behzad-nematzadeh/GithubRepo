package ir.behzadnematzadeh.githubrepo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import ir.behzadnematzadeh.githubrepo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val navHostFragment = NavHostFragment.create(R.navigation.nav_graph)

            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_container, navHostFragment)
                .setPrimaryNavigationFragment(navHostFragment)
                .commit()
        }
    }
}