package com.paige.trysomethingnew.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.paige.trysomethingnew.R
import com.paige.trysomethingnew.ui.fragment.BaseFragment

class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    @BindView(R.id.text_home)
    lateinit var textView: TextView

    @BindView(R.id.list)
    lateinit var list: RecyclerView

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

        val adapter = PagedAdapter()

        list.adapter = adapter
        list.layoutManager = GridLayoutManager(context!!, 2)

        homeViewModel.pagedListRestaurant.observe(this, Observer {
            adapter.submitList(it)
        })

        return root
    }
}