package com.kotlinblog.actors.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kotlinblog.actors.R
import com.squareup.picasso.Picasso

class MainAdapter(private var viewModel: MainViewModel,
                  private val context: Context)
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        viewModel.getActorAt(position)?.let {
            Picasso.with(context).load(it.image).into(holder.picture)
            holder.actorName.text = it.name
            holder.country.text = context.getString(R.string.from, it.country)
            holder.dob.text = context.getString(R.string.born, it.dob)
            holder.height.text = context.getString(R.string.height, it.height)
            holder.description.text = it.description
        }
    }

    override fun getItemCount(): Int {
        return viewModel.getMealListSize()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false))
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val picture by lazy { view.findViewById<ImageView>(R.id.ivActorImage) as ImageView }
        val actorName by lazy { view.findViewById<TextView>(R.id.tvActorName) as TextView }
        val country by lazy { view.findViewById<TextView>(R.id.tvCountry) as TextView }
        val dob by lazy { view.findViewById<TextView>(R.id.tvDob) as TextView }
        val height by lazy { view.findViewById<TextView>(R.id.tvHeight) as TextView }
        val description by lazy { view.findViewById<TextView>(R.id.tvDescription) as TextView }
    }
}