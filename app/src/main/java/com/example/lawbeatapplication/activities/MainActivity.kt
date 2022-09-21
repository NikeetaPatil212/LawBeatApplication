package com.example.lawbeatapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.lawbeatapplication.R
import com.example.lawbeatapplication.adapter.ViewPagerAdapter
import com.example.lawbeatapplication.dataclass.DataResponse
import com.example.lawbeatapplication.dataclass.ParentData
import com.example.lawbeatapplication.dataclass.SubCategory
import com.example.lawbeatapplication.factory.CategoryFactory
import com.example.lawbeatapplication.fragment.NewsFragment
import com.example.lawbeatapplication.repository.NewsRepository
import com.example.lawbeatapplication.viewmodel.CategoryViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    var arrayList: ArrayList<DataResponse> = ArrayList()
    var viewModel: CategoryViewModel? = null
    lateinit var toogle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPage = findViewById<ViewPager>(R.id.viewPager)

        viewModel = ViewModelProvider(this, CategoryFactory(NewsRepository())).get(CategoryViewModel::class.java)

        val adapter = ExpandedAdapter(this)
        recycleviewExp.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recycleviewExp.adapter = adapter

        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.IO) { viewModel!!.getCategory() }
        }
        viewModel!!.categoryData.observe(this, Observer {
            if (it?.statusCode == 200) {
                if (it.data.isNotEmpty()) {
                    for (getcategory in it.data) {
                        arrayList.add(getcategory)
                    }
                    for (listTitle in arrayList) {
                        tabLayout.addTab(tabLayout.newTab().setText(listTitle.name))
                    }
                }
                val data = it.data.map {
                    ParentData (it, subList = it?.subcat?.toMutableList()?: mutableListOf<SubCategory>())
                }
                adapter.ExpandedData(data)
                SetPagination(viewPage = viewPage, arrayList)

            } else {
                Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
            }
        })

        hamburgerImg.setOnClickListener {
            if (navDrawer.isOpen) {
                navDrawer.close()

            } else {
                navDrawer.open()
            }
        }
    }

    fun SetPagination(viewPage: ViewPager, list: List<DataResponse>) {
        val viewAdapter = ViewPagerAdapter(supportFragmentManager)
        for ((index, value) in list.withIndex()) {
            val name = list[index]
            viewAdapter.addFrag(NewsFragment.newInstance(name.tid, name.name), value.name)
        }
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        viewPage.offscreenPageLimit = list.size
        viewPage.adapter = viewAdapter
        viewPage.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPage.currentItem = tab.position

            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}