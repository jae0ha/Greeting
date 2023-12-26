package com.emgram.greeting.ui.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emgram.greeting.R
import com.emgram.greeting.data.Location

class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    private val latitudeTextView: TextView = view.findViewById(R.id.latitudeTextView)
    private val longitudeTextView: TextView = view.findViewById(R.id.longitudeTextView)

    fun bind(location: Location) {
        nameTextView.text = location.name
        latitudeTextView.text = location.latitude.toString()
        longitudeTextView.text = location.longitude.toString()
    }
}