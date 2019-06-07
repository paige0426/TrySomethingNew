package com.paige.trysomethingnew.ui.home

import android.content.Context
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
import com.paige.trysomethingnew.api.repositories.RestaurantRepository
import com.paige.trysomethingnew.ui.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    @BindView(R.id.text_home)
    lateinit var textView: TextView

    @BindView(R.id.list)
    lateinit var list: RecyclerView

    @Inject
    lateinit var restaurantRepository: RestaurantRepository

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}