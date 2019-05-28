package com.paige.trysomethingnew

import android.os.AsyncTask
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import com.paige.trysomethingnew.api.response.YelpResponse
import com.paige.trysomethingnew.di.component.DaggerRestaurantServiceComponenet
import com.paige.trysomethingnew.di.component.DaggerYelpServiceComponent
import com.paige.trysomethingnew.di.module.ContextModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val daggerYelpServiceComponent = DaggerYelpServiceComponent.builder().build()
        val yelpApiService = daggerYelpServiceComponent.getYelpApiService()

        val daggerRestaurantServiceComponenet = DaggerRestaurantServiceComponenet.builder()
            .contextModule(ContextModule(this))
            .build()
        val restaurantDao = daggerRestaurantServiceComponenet.restaurantDao()

        val call = yelpApiService.loadRestaurants("delis", 37.786882, -122.399972)
        call.enqueue(object : Callback<YelpResponse> {
            override fun onFailure(call: Call<YelpResponse>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<YelpResponse>, response: Response<YelpResponse>) {
                if (response.isSuccessful) {
                    val restaurantList = response.body()
                    if (restaurantList != null) {
                        Timber.d(restaurantList.toString())
                        CoroutineScope(Dispatchers.IO).launch {
                            restaurantDao.insert(restaurantList?.restaurantList)
                        }
                        AsyncTask.execute {
                            Timber.i(restaurantDao.getAllRestaurants().toString())
                        }
                    }
                } else {
                    Timber.e(response.errorBody()?.string())
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
