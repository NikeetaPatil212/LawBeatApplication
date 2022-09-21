package com.example.lawbeatapplication.adapter

import android.content.Context
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lawbeatapplication.R
import com.example.lawbeatapplication.dataclass.NewsData

class NewPagingAdapter(val context: Context, list:List<NewsData>) :
    PagingDataAdapter<NewsData, NewPagingAdapter.NewsViewHolder>(diffCallback) {

    private lateinit var mListener: OnItemClickListener
    var list: List<NewsData> = list


    companion object {
        const val VIEWFIRST = 1
        const val VIEWDEFAULT = 2
        val diffCallback = object : DiffUtil.ItemCallback<NewsData>() {
            override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
                return  oldItem == newItem
            }

        }
    }


    abstract class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(position: Int,listner:OnItemClickListener)

    }

    inner class NewsViewHolder1(itemView: View) : NewsViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.tvNewNews)
        var textView2: TextView = itemView.findViewById(R.id.tvNewAuthor)
        var imageView2: ImageView = itemView.findViewById(R.id.view1)
        var time: TextView = itemView.findViewById(R.id.tvNewTime)

        override fun bind(position: Int,listner:OnItemClickListener) {
            val p = getItem(position)
            itemView.setOnClickListener(){
                if (listner != null) {
                    var position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listner.onItemclick(position, getNewsResponseData = p!!)

                    }
                }
            }
            textView.text = p?.title
            textView2.text = p?.fieldAuthorName
            time.text = p?.readingTime
            context?.let { Glide.with(it).load(p?.fieldNewsImage).into(imageView2) }

        }

    }

    inner class NewsViewHolder2(itemView: View) : NewsViewHolder(itemView) {
        var textViewDefault: TextView = itemView.findViewById(R.id.tvOldNews)
        var textView2Default: TextView = itemView.findViewById(R.id.tvOldName)
        var imageView2Default: ImageView = itemView.findViewById(R.id.imgOldNews)
        var time: TextView = itemView.findViewById(R.id.tvOldAuthor)


        override fun bind(position: Int,listner:OnItemClickListener) {
            val p = getItem(position)
            itemView.setOnClickListener(){
                if (listner != null) {
                    var position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listner.onItemclick(position, getNewsResponseData = p!!)

                    }
                }
            }
            textViewDefault.text = p?.title
            textView2Default.text = p?.fieldAuthorName
            time.text = p?.readingTime
            context?.let {
                Glide.with(it).load(p?.fieldNewsImage).into(imageView2Default)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        if (viewType == 1) {
            return NewsViewHolder1(
                LayoutInflater.from(parent.context).inflate(R.layout.item_new_news, parent, false)
            )
        } else {
            return NewsViewHolder2(
                LayoutInflater.from(parent.context).inflate(R.layout.item_old_news, parent, false)
            )
        }
    }



    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 1
        else 2

    }

    interface OnItemClickListener {
        fun onItemclick(
            position: Int,getNewsResponseData: NewsData
        )
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (position == 0) {
            (holder as NewsViewHolder).bind(position,mListener)
        } else {

            (holder as NewsViewHolder2).bind(position,mListener)
        }

    }

}



