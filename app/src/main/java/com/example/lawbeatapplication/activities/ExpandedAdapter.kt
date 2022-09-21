package com.example.lawbeatapplication.activities

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawbeatapplication.R
import com.example.lawbeatapplication.dataclass.Constants
import com.example.lawbeatapplication.dataclass.ParentData
import com.example.lawbeatapplication.dataclass.SubCategory

class ExpandedAdapter (val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList = mutableListOf<ParentData>()
    private var selectedPosition = RecyclerView.NO_POSITION

    var onCatSelected : ((name : String)->Unit)? = null

    fun setOnCatSelectedListner(onCatSelected : (name : String)->Unit){
        this.onCatSelected = onCatSelected
    }

    fun ExpandedData(dataList: List<ParentData>) {
        this.dataList = dataList.toMutableList()
        notifyDataSetChanged()
    }
    private lateinit var mListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.PARENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parent, parent, false)
            ParentViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_child, parent, false)
            ChildViewHolder(view)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val data = dataList[position]
        if (data.type == Constants.PARENT) {
            holder as ParentViewHolder
            holder.apply {
                tvParent.setText(data.parentTitle?.name)
                imagePlus.visibility = if (dataList.get(position).subList.isEmpty()){
                    View.GONE
                }
                else
                {
                    View.VISIBLE
                }
                imagePlus.setOnClickListener {
                    expandOrCollapseParentItem(data,position)
                }
                holder.itemView.setOnClickListener {
                    val category =  dataList.get(position).parentTitle?.name
                    onCatSelected?.invoke(category!!)

                }
            }

        } else {
            holder as ChildViewHolder
            holder.apply {
                val singleservice = data.subList.first()
                tvChild.setText(singleservice.name)

                holder.tvChild.setOnClickListener {
                    selectedPosition = position
                    holder.itemView.isSelected = true
                    notifyDataSetChanged()
                    mListener.onItemclick(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size

    }
    override fun getItemViewType(position: Int): Int = dataList[position].type

    private fun expandOrCollapseParentItem(singleBoarding: ParentData, position: Int) {
        if (singleBoarding.isExpanded) {
            collapseParentRow(position)
        } else {
            expandParentRow(position)
        }
    }

    private fun collapseParentRow(position: Int) {
        Log.d("collapse","$position")
        val currentBoardingRow = dataList[position]
        val services = currentBoardingRow.subList
        dataList[position].isExpanded = false
        if (dataList[position].type == Constants.PARENT) {
            services.forEach { _ ->
                dataList.removeAt(position + 1)
            }
            notifyDataSetChanged()
        }
    }

    private fun expandParentRow(position: Int) {
        Log.d("expand","Expand,$position")
        val currentBoardingRow = dataList[position]
        val services = currentBoardingRow.subList
        currentBoardingRow.isExpanded = true
        var nextPosition = position
        if (currentBoardingRow.type == Constants.PARENT) {

            services.forEach { service ->
                val parentModel = ParentData()
                parentModel.type = Constants.CHILD
                val subList: ArrayList<SubCategory> = ArrayList()
                subList.add(service)
                parentModel.subList = subList
                dataList.add(++nextPosition, parentModel)
            }
            notifyDataSetChanged()
        }
    }

    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvParent = itemView.findViewById<TextView>(R.id.tvParent)
        val imagePlus = itemView.findViewById<ImageView>(R.id.imagePlus)


    }

    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvChild = itemView.findViewById<TextView>(R.id.categoryListItem)

    }
    interface OnItemClickListener {
        fun onItemclick(
            position: Int
        )
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this. mListener = listener
    }
    fun resetItemSelection(){
        selectedPosition = RecyclerView.NO_POSITION
        notifyDataSetChanged()
    }



}