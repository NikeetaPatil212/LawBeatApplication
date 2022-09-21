package com.example.lawbeatapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.lawbeatapplication.fragment.NewsFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val mFragmentTitleList: MutableList<String> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }


    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size; }

    fun addFrag(fragment: NewsFragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}