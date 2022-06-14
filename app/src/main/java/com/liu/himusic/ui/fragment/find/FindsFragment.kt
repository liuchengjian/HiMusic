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
import com.liu.himusic.ui.adapter.FindAdapter
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

class FindsFragment : BaseHomeFragment(), OnRefreshListener, OnLoadMoreListener {
    var binding: FragmentFindBinding? = null
    private var mRecyclerView: RecyclerView? = null
    private var mRefreshLayout: SmartRefreshLayout? = null
    private var mEmptyView: EmptyView? = null
    private var decoration: DividerItemDecoration? = null
    private var adapter: FindAdapter? = null
    private lateinit var viewMolder: FindViewModel
    private var findBean: FindBean? = null
    private var findBallList: List<FindBall>? = null
    private var cursor: String? = null

    companion object {
        @JvmStatic
        fun newInstance(categoryId: String?): FindsFragment {
            val args = Bundle()
//            args.putString("categoryId", categoryId)
            val fragment =
                FindsFragment()
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
                    finishRefresh( true)
                    return
                }
                updateUI(data)
                viewMolder.findBall()
            }
        })
        LiveDataBus.get().with("FindBall").observe(this, object : Observer<List<FindBall>> {
            override fun onChanged(data: List<FindBall>?) {
                if (data == null) {
                    finishRefresh( true)
                    return
                }
                findBallList = data
//                if (adapter!!.getItem(0) !is HeaderTabItem && adapter!!.getItem(1) !is HeaderTabItem) {
//                    val index = if (adapter!!.getItem(0) is BannerNewItem) 1 else 0
//                    adapter!!.addItemAt(index, HeaderTabItem(data!!), true)
//                }
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
            adapter = FindAdapter()
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

    private val dataItems = mutableListOf<BlockBean>()
    private val newDataItems = mutableListOf<HiDataItem<*, *>>()
    fun updateUI(data: FindBean?) {
        if (!isAlive()) return
        if (data == null) return
        if (data.isRefresh) {
            dataItems.clear()
        }
        findBean = data
        newDataItems.clear()
        cursor = data.cursor

        dataItems.addAll(data.blocks)

        if (data.blocks.isNotEmpty()) {
            adapter!!.setList(dataItems)
            finishRefresh(data.isHasMore)
        }
    }



    private fun finishRefresh(isHasMore: Boolean) {
        val state = mRefreshLayout!!.state
        if (state.isFooter && state.isOpening) {
            mRefreshLayout!!.finishLoadMore()
            if (isHasMore) {
            }
        } else if (state.isHeader && state.isOpening) {
            mRefreshLayout!!.finishRefresh()
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
        if(findBean==null){
            return
        }
        if(findBean!!.isHasMore){
            toFind(false, cursor)
        }else{
            finishRefresh(true)
            mRefreshLayout!!.setNoMoreData(false)
        }
    }

    private fun toFind(refresh: Boolean, cursor: String?) {
        viewMolder.toFind(refresh, cursor)
    }

}