package com.liu.himusic.ui.fragment.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
import com.liu.himusic.R
import com.liu.himusic.databinding.FragmentFindBinding
import com.liu.himusic.model.bean.*
import com.liu.himusic.ui.fragment.BaseHomeFragment
import com.liu.himusic.ui.fragment.find.item.*
import com.liu.himusic.ui.viewmodel.FindViewModel
import com.liucj.lib_common.item.HiAdapter
import com.liucj.lib_common.item.HiDataItem
import com.liucj.lib_common.livedata.LiveDataBus
import com.liucj.lib_common.view.EmptyView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

class FindFragment : BaseHomeFragment(), OnRefreshListener, OnLoadMoreListener {
    var binding: FragmentFindBinding? = null
    private var mRecyclerView: RecyclerView? = null
    private var mRefreshLayout: SmartRefreshLayout? = null
    private var mEmptyView: EmptyView? = null
    private var decoration: DividerItemDecoration? = null
    private var adapter: HiAdapter? = null
    private lateinit var viewMolder: FindViewModel
    private var findBean: FindBean? = null
    private var findBallList: List<FindBall>? = null
    private var cursor: String? = null

    companion object {
        @JvmStatic
        fun newInstance(categoryId: String?): FindFragment {
            val args = Bundle()
//            args.putString("categoryId", categoryId)
            val fragment =
                FindFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFindBinding.inflate(inflater)
        binding!!.root.fitsSystemWindows = true
        viewMolder = ViewModelProvider(this)[FindViewModel::class.java]
        initView()
        initRecyclerView()
        initData()
        mRefreshLayout!!.autoRefresh()

        return binding!!.root
    }

    private fun initData() {
        LiveDataBus.get().with("FindPage").observe(this, object : Observer<FindBean> {
            override fun onChanged(data: FindBean?) {
                if (data == null) {
                    finishRefresh(dataItems, true)
                    return
                }
                updateUI(data)
                viewMolder.findBall()
            }
        })
        LiveDataBus.get().with("FindBall").observe(this, object : Observer<List<FindBall>> {
            override fun onChanged(data: List<FindBall>?) {
                if (data == null) {
                    finishRefresh(dataItems, true)
                    return
                }
                findBallList = data
                if (adapter!!.getItem(0) !is HeaderTabItem && adapter!!.getItem(1) !is HeaderTabItem) {
                    val index = if (adapter!!.getItem(0) is BannerNewItem) 1 else 0
                    adapter!!.addItemAt(index, HeaderTabItem(data!!), true)
                }
            }
        })
    }

    private fun initView() {
        setNavigationLeftDrawer(binding!!.navigationBar)
    }

    private fun initRecyclerView() {
        mRecyclerView = binding!!.recyclerView
        mRefreshLayout = binding!!.refreshLayout
        mRefreshLayout!!.setOnRefreshListener(this)
        mRefreshLayout!!.setOnLoadMoreListener(this)
//        mEmptyView = binding.emptyView
        if (adapter == null) {
            adapter = HiAdapter(requireContext())
        }
        mRecyclerView!!.adapter = adapter
        mRecyclerView!!.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mRecyclerView!!.itemAnimator = null

        //默认给列表中的Item 一个 10dp的ItemDecoration
        decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        decoration!!.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.list_divider
            )!!
        )
        mRecyclerView!!.addItemDecoration(decoration!!)

    }

    private var blockCodeArray = ArrayList<String>()
    private val dataItems = mutableListOf<HiDataItem<*, *>>()
    private val newDataItems = mutableListOf<HiDataItem<*, *>>()
    fun updateUI(data: FindBean?) {
        if (!isAlive()) return
        if (data == null) return
        if (data.isRefresh) {
            dataItems.clear()
        }
        newDataItems.clear()
        cursor = data.cursor
        if (data.blocks.isNotEmpty()) {
            data.blocks.let {
                it.forEachIndexed { index, item: BlockBean ->
//                for (map in it) {
                    val blockCode: String = item.blockCode
                    val showType: String = item.showType
                    if (blockCode == "HOMEPAGE_BANNER" && showType == "BANNER") {
                        //轮播图
                        val extInfoStr: String = Gson().toJson(item.extInfo)
                        val extInfo: ExtInfo = Gson().fromJson(extInfoStr, ExtInfo::class.java)
                        dataItems.add(BannerNewItem(extInfo.banners))
                        newDataItems.add(BannerNewItem(extInfo.banners))
                    } else if ((blockCode == "HOMEPAGE_BLOCK_PLAYLIST_RCMD" && showType ==
                                "HOMEPAGE_SLIDE_PLAYLIST")
                        || blockCode == "HOMEPAGE_BLOCK_MGC_PLAYLIST" && showType == "HOMEPAGE_SLIDE_PLAYLIST"
                        || blockCode == "HOMEPAGE_BLOCK_OFFICIAL_PLAYLIST" && showType == "HOMEPAGE_SLIDE_PLAYLIST"
//                        || blockCode == "HOMEPAGE_BLOCK_LISTEN_LIVE" && showType == "HOMEPAGE_SLIDE_LISTEN_LIVE"
//                        || blockCode == "HOMEPAGE_BLOCK_YUNCUN_PRODUCED" && showType == "YUNCUN_PRODUCED"
                    ) {
                        dataItems.add(HeaderItem(item.uiElement))
                        dataItems.add(RecommendImageItem(item))
                        newDataItems.add(HeaderItem(item.uiElement))
                        newDataItems.add(RecommendImageItem(item))
                    } else if (blockCode == "HOMEPAGE_BLOCK_HOT_TOPIC" &&
                        showType == "HOT_TOPIC"
                    ) {
                        val uiElementBean = item.uiElement
                        val creatives = item.creatives
                        dataItems.add(ViewPagerGalleryItem(uiElementBean, creatives))
                        newDataItems.add(ViewPagerGalleryItem(uiElementBean, creatives))
                    } else if (blockCode == "HOMEPAGE_MUSIC_MLOG"
                        && showType == "HOMEPAGE_SLIDE_PLAYABLE_MLOG"
                    ) {
                        val uiElementBean = item.uiElement
                        val list: List<ExtInfoBean> = item.extInfoList()
                        dataItems.add(HeaderItem(uiElementBean))
                        dataItems.add(AutoPlayItem(list))
                        newDataItems.add(HeaderItem(uiElementBean))
                        newDataItems.add(AutoPlayItem(list))
                    } else if ((blockCode == "HOMEPAGE_BLOCK_STYLE_RCMD"
                                && showType == "HOMEPAGE_SLIDE_PLAYLIST")
                        || (blockCode == "HOMEPAGE_VOICELIST_RCMD" && showType == "SLIDE_PODCAST_VOICE_MORE_TAB")
                        || (blockCode == "HOMEPAGE_BLOCK_NEW_ALBUM_NEW_SONG" && showType == "HOMEPAGE_NEW_SONG_NEW_ALBUM")
                        || (blockCode == "HOMEPAGE_BLOCK_TOPLIST" && showType == "HOMEPAGE_SLIDE_TOPLIST")
                    ) {
                        val uiElementBean = item.uiElement
                        val creatives = item.creatives
                        val threeSongItem = ThreeSongItem(uiElementBean,creatives)
                        dataItems.add(threeSongItem)
                        newDataItems.add(threeSongItem)
                    }
                }
            }
        }
        if (data.isRefresh) {
            finishRefresh(dataItems, data.isHasMore)
        } else {
            finishRefresh(newDataItems, data.isHasMore)
        }

    }


    private fun isHasItemCode(key: String): Boolean {
        var isResult = false
        if (blockCodeArray.isEmpty()) {
            return isResult
        }
        if (Gson().toJson(blockCodeArray).contains(key)) {
            isResult = true
        }
        return isResult
    }

    private fun finishRefresh(dataItems: List<HiDataItem<*, out ViewHolder>>, isHasMore: Boolean) {
        val state = mRefreshLayout!!.state
        if (state.isFooter && state.isOpening) {
            mRefreshLayout!!.finishLoadMore()
            if (isHasMore) {
                adapter!!.addItems(dataItems, true)
            }
        } else if (state.isHeader && state.isOpening) {
            mRefreshLayout!!.finishRefresh()
            adapter!!.clearItems()
            adapter!!.addItems(dataItems, true)
        }
//        if (dataItems.isEmpty()) {
//            mEmptyView!!.visibility = View.GONE
//        } else {
////            adapter.setEmptyView(mEmptyView);
//            mEmptyView!!.visibility = View.VISIBLE
//        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        cursor = null
        toFind(true, cursor)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        toFind(false, cursor)

    }

    private fun toFind(refresh: Boolean, cursor: String?) {
        viewMolder.toFind(refresh, cursor)
    }

}