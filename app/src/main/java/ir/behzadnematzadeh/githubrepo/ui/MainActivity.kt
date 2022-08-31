package ir.behzadnematzadeh.githubrepo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import ir.behzadnematzadeh.githubrepo.R

class MainActivity : AppCompatActivity() {

    companion object {
        const val NavHostTag = "NavHostTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val navHostFragment = obtainNavHostFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_container, navHostFragment, NavHostTag)
                .setPrimaryNavigationFragment(navHostFragment)
                .commit()
        }
    }

    private fun obtainNavHostFragment(): NavHostFragment {
        return (supportFragmentManager.findFragmentByTag(NavHostTag) as? NavHostFragment)
            ?: NavHostFragment.create(R.navigation.nav_graph)
    }
}