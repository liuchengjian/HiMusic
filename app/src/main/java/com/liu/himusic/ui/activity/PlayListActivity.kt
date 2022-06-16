package com.liu.himusic.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.liu.himusic.R
import com.liu.himusic.databinding.ActivityPlayListBinding
import com.liu.himusic.model.bean.PlatListBean
import com.liu.himusic.ui.adapter.PlayListAdapter
import com.liu.himusic.ui.viewmodel.PlayListViewModel
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.utils.PixUtils
import com.liucj.lib_common.view.loadUrl
import com.lzx.starrysky.utils.getResourceId

class PlayListActivity : AppCompatActivity() {
    private lateinit var viewMolder: PlayListViewModel
    private lateinit var binding: ActivityPlayListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_play_list)
        binding = ActivityPlayListBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initView()
    }

    private fun initView() {
        viewMolder = ViewModelProvider(this)[PlayListViewModel::class.java]
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvPlaylistSong.layoutManager =linearLayoutManager
        var adapter =PlayListAdapter(ArrayList())
        binding.rvPlaylistSong.adapter = adapter
//        val decoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
//        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_divider)!!)
//        binding.rvPlaylistSong.addItemDecoration(decoration)
        viewMolder.toPlayList()
        LiveDataBus.get().with("PlayList").observe(this, object : Observer<PlatListBean> {
            override fun onChanged(data: PlatListBean) {

                var size = data!!.dailySongs.size-1;
                val randoms = (0..size).random()
                binding!!.ivCoverBg.loadUrl(data!!.dailySongs[randoms].al.picUrl)
                adapter.setSongReasons(data!!.recommendReasons)
                adapter.setList(data!!.dailySongs)
            }
        })
    }
}