package com.liu.himusic.ui.adapter.provider

import android.text.TextUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.liu.himusic.BR
import com.liu.himusic.R
import com.liu.himusic.databinding.PlayListItemBinding
import com.liu.himusic.model.bean.PlatListBean
import com.liu.himusic.model.bean.PlatListBean.SongReason
import com.liu.himusic.model.bean.Song
import com.youth.banner.util.BannerUtils

class PlayListItemProvider(private var songReasons: List<PlatListBean.SongReason>) :
    BaseItemProvider<Song>() {

    fun setSongReasons(songReasons: List<SongReason>) {
        this.songReasons = songReasons
    }

    override val itemViewType: Int
        get() = 0
    override val layoutId: Int
        get() = R.layout.play_list_item

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, song: Song) {
        val binding = helper.getBinding<PlayListItemBinding>()
        BannerUtils.setBannerRound(binding!!.image,5.0f)
        binding!!.image.setImageUrl(song.al.picUrl)
        binding!!.setVariable(BR.song, song)
        if (songReasons != null && songReasons.isNotEmpty()) {
            var reason = ""
            for (songReason in songReasons) {
                if (song.id == songReason.songId) {
                    reason = songReason.reason
                    break
                }
            }
            if (TextUtils.isEmpty(reason)) {
                binding!!.reason.visibility = View.GONE
            } else {
                binding!!.reason.visibility = View.VISIBLE
                binding!!.reason.text = reason
            }
        }
        if(song.sq==null&&song.hr==null){
            binding!!.sqAndHr.visibility = View.GONE
        }else{
            binding!!.sqAndHr.visibility = View.VISIBLE
            if(song.hr!=null){
                binding!!.sqAndHr.text = "Hi-Res"
            }
            if(song.sq!=null){
                binding!!.sqAndHr.text = "SQ"
            }
        }

    }
}