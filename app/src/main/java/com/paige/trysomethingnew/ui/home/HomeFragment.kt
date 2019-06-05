package com.paige.trysomethingnew.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.paige.trysomethingnew.R
import com.paige.trysomethingnew.di.component.DaggerRestaurantRepositoryComponent
import com.paige.trysomethingnew.di.module.ContextModule

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    @BindView(R.id.text_home)
    lateinit var textView: TextView

    @BindView(R.id.list)
    lateinit var list: RecyclerView

    private val restaurantRepository by lazy {
        DaggerRestaurantRepositoryComponent.builder()
            .contextModule(ContextModule(context!!))
            .build()
            .getRestaurantRepository()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        ButterKnife.bind(this, root)

        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })

        val adapter = RestaurantsAdapter()

        list.adapter = adapter
        list.layoutManager = GridLayoutManager(context!!, 2)

        restaurantRepository.fetchRestaurants("steak", 37.786882, -122.399972) {
            adapter.submitList(it)
        }

        return root
    }
}