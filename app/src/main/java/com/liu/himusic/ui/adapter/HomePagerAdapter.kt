package com.liu.himusic.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomePagerAdapter(fm: FragmentManager, behavior: Int, fragments: List<Fragment>) :
    FragmentPagerAdapter(fm, behavior) {
    var fragmentList: List<Fragment> = fragments
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = fragmentList[position]
        return fragment!!
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}