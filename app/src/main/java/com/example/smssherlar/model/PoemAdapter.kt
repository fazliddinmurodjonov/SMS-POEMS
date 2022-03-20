package com.example.smssherlar.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smssherlar.R
import com.example.smssherlar.databinding.ItemPoemBinding

class PoemAdapter(var poemList: ArrayList<Poem>) :
    RecyclerView.Adapter<PoemAdapter.MyViewHolder>() {
    lateinit var adapterListener: OnMyItemClickListener

    interface OnMyItemClickListener {
        fun onClick(poem: Poem,position: Int)
    }

    fun setOnMyItemClickListener(listener: OnMyItemClickListener) {
        adapterListener = listener
    }

    inner class MyViewHolder(var bind: ItemPoemBinding) : RecyclerView.ViewHolder(bind.root) {
        fun onBind(poem: Poem,position: Int) {
            bind.PoemName.text = poem.poemName.toString()
            bind.Poem.text = poem.poem.toString()
            if (poem.poemLiked == true) {
                bind.PoemLike.visibility = View.VISIBLE
            } else {
                bind.PoemLike.visibility = View.INVISIBLE
            }
            bind.root.setOnClickListener {
                adapterListener.onClick(poem,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            ItemPoemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val poem = poemList[position]
        holder.onBind(poem,position)
    }

    override fun getItemCount(): Int = poemList.size
}