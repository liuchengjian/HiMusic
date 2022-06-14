package com.liu.himusic

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.liu.himusic.databinding.ActivityMainBinding
import com.liu.himusic.ui.fragment.find.FindFragment
import com.liu.himusic.ui.fragment.mine.MineFragment
import com.liu.himusic.ui.adapter.HomePagerAdapter
import com.liu.himusic.ui.fragment.find.FindsFragment
import com.liu.himusic.ui.widget.SongPlayManager
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.utils.StatusBarKt
import com.roughike.bottombar.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    private lateinit var listFragment: ArrayList<Fragment>
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarKt.setStatusBar(this, true, statusBarColor = Color.TRANSPARENT, translucent = true)
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initBottomBar()
        LiveDataBus.get().with("drawer_layout").observe(this, object : Observer<Boolean> {
            override fun onChanged(t: Boolean?) {
                if (t == true) {
                    if (binding!!.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                        binding!!.drawerLayout.closeDrawer(Gravity.LEFT)
                    } else {
                        binding!!.drawerLayout.openDrawer(Gravity.LEFT)
                    }
                }
            }
        })
        SongPlayManager.getInstance().init(this)
        bottom_song_playbar.initEvent(this)
    }

    private fun initBottomBar() {
        listFragment = ArrayList()
        listFragment.add(FindsFragment.newInstance(null))
        listFragment.add(FindFragment.newInstance(null))
        listFragment.add(MineFragment.newInstance(null))
        listFragment.add(FindFragment.newInstance(null))
        listFragment.add(FindFragment.newInstance(null))
        binding!!.navViewpager.adapter = HomePagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            listFragment
        )
        binding!!.navViewpager.addOnPageChangeListener(this)
        binding!!.bottomBar.setOnTabSelectListener(OnTabSelectListener { tabId ->
            onPageScrollStateChanged(tabId)
        })

    }

    //滑动时
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }
    //页面选中时
    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                binding!!.bottomBar.selectTabWithId(R.id.tab_find)
            }
            1 -> {
                binding!!.bottomBar.selectTabWithId(R.id.tab_podcast)
            }
            2 -> {
                binding!!.bottomBar.selectTabWithId(R.id.tab_mine)
            }
            3 -> {
                binding!!.bottomBar.selectTabWithId(R.id.tab_follow)
            }
            4 -> {
                binding!!.bottomBar.selectTabWithId(R.id.tab_yuncun)
            }
        }
    }

    //滑动状态改变时
    override fun onPageScrollStateChanged(state: Int) {
        when (state) {
            R.id.tab_find -> {
                binding!!.navViewpager.setCurrentItem(0, false)
            }
            R.id.tab_podcast -> {
                binding!!.navViewpager.setCurrentItem(1, false)
            }
            R.id.tab_mine -> {
                binding!!.navViewpager.setCurrentItem(2, false)
            }
            R.id.tab_follow -> {
                binding!!.navViewpager.setCurrentItem(3, false)
            }
            R.id.tab_yuncun -> {
                binding!!.navViewpager.setCurrentItem(4, false)
            }
        }
    }

}