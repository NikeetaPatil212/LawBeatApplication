package com.example.lawbeatapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawbeatapplication.R
import com.example.lawbeatapplication.activities.NewsDetailActivity
import com.example.lawbeatapplication.adapter.NewPagingAdapter
import com.example.lawbeatapplication.dataclass.NewsData
import com.example.lawbeatapplication.factory.NewsFactory
import com.example.lawbeatapplication.repository.NewsRepository
import com.example.lawbeatapplication.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    lateinit var adapter: NewPagingAdapter
    var list: List<NewsData> = listOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val newsRecylerview = view.findViewById<RecyclerView>(R.id.newsRecylerview)
        val tid = arguments?.getInt(ARG_PARAM1) ?: 1
        Log.d("tid", tid.toString())


        val repository = NewsRepository()
        viewModel = ViewModelProvider(this, NewsFactory(repository, tid)).get(NewsViewModel::class.java)

        adapter = NewPagingAdapter(requireContext(),list)

        newsRecylerview.adapter = adapter
        newsRecylerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
        adapter.setOnItemClickListener(object: NewPagingAdapter.OnItemClickListener {
            override fun onItemclick(position: Int,getNewsResponseData: NewsData) {
                var intent = Intent(requireContext(), NewsDetailActivity::class.java)
                intent.putExtra("Image", getNewsResponseData.fieldNewsImage)
                intent.putExtra("Category", getNewsResponseData.categoryName)
                intent.putExtra("Title", getNewsResponseData.title)
                intent.putExtra("author", getNewsResponseData.fieldAuthorName)
                intent.putExtra("time", getNewsResponseData.readingTime)
                intent.putExtra("Date", getNewsResponseData.rawDate)
                intent.putExtra("Body", getNewsResponseData.body)
                startActivity(intent)
            }
        })
    }
    companion object {

        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1.toInt())
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}