package com.paige.trysomethingnew.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.paige.trysomethingnew.R
import com.paige.trysomethingnew.ui.fragment.SignInFragment
import com.paige.trysomethingnew.ui.fragment.SignUpFragment

class LoginActivity : BaseActivity() {

    @BindView(R.id.login_view_pager)
    lateinit var viewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)

        viewPager.adapter = PagerAdapter(supportFragmentManager)
    }

    private class PagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> SignInFragment()
                else -> SignUpFragment()
            }
        }

        override fun getCount(): Int = NUM_ITEMS

        override fun getPageWidth(position: Int): Float {
            return 0.9f
        }
    }

    companion object {
        private const val NUM_ITEMS = 2
    }
}