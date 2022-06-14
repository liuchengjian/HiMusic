package com.liu.himusic.ui.fragment.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liu.himusic.databinding.FragmentMineBinding
import com.liu.himusic.ui.fragment.BaseHomeFragment
import com.liucj.lib_common.view.loadUrl

class MineFragment : BaseHomeFragment() {
    var binding: FragmentMineBinding? = null

    companion object {
        @JvmStatic
        fun newInstance(categoryId: String?): MineFragment {
            val args = Bundle()
//            args.putString("categoryId", categoryId)
            val fragment =
                MineFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMineBinding.inflate(inflater)
        binding!!.root.fitsSystemWindows = true
        initView()
        return binding!!.root
    }

    private fun initView() {
        setNavigationLeftDrawer(binding!!.navigationBar)
        binding!!.jzVideo.setUp(
            "http://vodkgeyttp9.vod.126.net/cloudmusic/vIBdW4T0_3439532216_shd.mp4?ts=1653981099&rid=0723F1A15C72C79B61AE1F302E350CBC&rl=3&rs=gihtfoytaumsAZJNSofuIsNRAQZYRVUk&sign=38615796e7ef08eb8605aa38ae4b2e79&ext=oK2aC9Qh0Z10I4UlRn0JXYw4FDXLEeRrYjyul35xFfcTnaWpo1OBAryrVMV4f04yAj0FvBoFb72FH1bcAggMX8mS5FlVvVWgz6i1PKWj5zZ7eawW1ktn%2FERv03p1J8WaA0OicQFb5V04aJ9WSKi1lcf6z4pEGS08z%2FElMuMDA6LbOyhIfvxEjd0KZNCng7%2BojeSqy3w6veNuYCPposoXPlTvOJIO4%2FKp10fHYaRcHcOO61b9wrV6UKdKWstpRHCI",
            "饺子闭眼睛"
        );
        binding!!.jzVideo.posterImageView.loadUrl("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
    }

}