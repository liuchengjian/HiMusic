package com.liu.himusic.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.liu.himusic.R
import com.liu.himusic.model.api.FindApi
import com.liu.himusic.model.bean.*
import com.liu.himusic.model.net.*
import com.liu.himusic.ui.widget.SongPlayManager
import com.liucj.lib_common.view.loadUrl
import com.liucj.lib_network.restful_kt.HiCallback
import com.liucj.lib_network.restful_kt.HiResponse
import com.lzx.starrysky.SongInfo

internal class ThreeSongAdapter internal constructor(
    private val dataList: List<CreativesBean>
) :
    RecyclerView.Adapter<ThreeSongAdapter.ThreeSongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreeSongViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_three_song, parent, false)
        return ThreeSongViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThreeSongViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.llSongContainer.removeAllViews()
        var creativesBean = dataList[position]
        for (resource in creativesBean.resources) {
            val itemChildThreeSong: View =
                View.inflate(context, R.layout.item_child_three_song, null)
            var ivThreeSong: ImageView = itemChildThreeSong.findViewById(R.id.iv_three_song)
            var tvThreeSongTitle: TextView =
                itemChildThreeSong.findViewById(R.id.tv_three_song_title)
            var tvThreeSongUser: TextView = itemChildThreeSong.findViewById(R.id.tv_three_song_user)
            var tvThreeSongDesc: TextView = itemChildThreeSong.findViewById(R.id.tv_three_song_desc)

            val resourceExtInfo: ResourceExtInfoBean? = resource!!.resourceExtInfo
            val song: Song = resourceExtInfo!!.song
            val artist = resourceExtInfo.artists[0]
            ivThreeSong.loadUrl(song.al.picUrl)
            tvThreeSongTitle.text = song.name
            tvThreeSongUser.text = "- " + artist.name
            if (resource.uiElement.subTitle != null && !TextUtils.isEmpty(resource.uiElement.subTitle.title)) {
                tvThreeSongDesc.visibility = View.VISIBLE
                tvThreeSongDesc.text = resource.uiElement.subTitle.title
            } else {
                tvThreeSongDesc.visibility = View.GONE
            }
            holder.llSongContainer.addView(itemChildThreeSong)
            itemChildThreeSong.setOnClickListener {
                var songInfo = SongInfo()
                songInfo.songName = song.name
                songInfo.songId = song.id.toString()
                songInfo.artist = artist.name
                songInfo.songCover = song.al.picUrl
                addSong(songInfo)
            }
        }

    }

    fun addSong(songInfo: SongInfo) {

        ApiFactory.create(FindApi::class.java).toSongUrl(songInfo.songId)
            .enqueue(object : HiCallback<BaseModel<List<SongPlayBean>>> {
                override fun onSuccess(response: HiResponse<BaseModel<List<SongPlayBean>>>) {
                    if (response.code == 200) {
                        SongPlayManager.getInstance().addSong(songInfo)
                        if (response.data != null) {
                            val song = response.data!!.data[0]
                            if (song!=null) {
                                songInfo.songUrl =song.url
                                songInfo.duration = song.size
                                SongPlayManager.getInstance().addSongAndPlay(songInfo)
                            }
                        }
                    }
                }

                override fun onFailed(throwable: Throwable) {

                }
            })
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    open class ThreeSongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llSongContainer: LinearLayout = view.findViewById(R.id.ll_song_container)

    }

}

