package robert.pakpahan.jetprofinal.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import robert.pakpahan.jetprofinal.R
import robert.pakpahan.jetprofinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupBottomNav()

    }

    private fun setupBottomNav() {
        val bottomNavigationView = binding?.bottomNavMain
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        if (bottomNavigationView != null) {
            NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment.navController
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

}