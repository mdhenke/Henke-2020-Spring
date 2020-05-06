package com.example.myapplication

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import com.example.myapplication.DriverFragment.OnListFragmentInteractionListener
import com.example.myapplication.models.Driver
import com.example.myapplication.services.Helper
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_drivers.view.*

/**
 * [RecyclerView.Adapter] that can display a [Driver] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyDriverRecyclerViewAdapter(
    private val mValues: List<Driver>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyDriverRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Driver
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_drivers, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        var t1 : String = "" + item.distance + " miles away"
        holder.mDistanceView.text =
            Helper.distanceInMiles(HomeActivity.currentLocation!!.latitude,
                HomeActivity.currentLocation!!.longitude, item.lat!!, item.long!!)
        holder.mNameView.text = item.name
        t1 = "\$${item.rate}/hr"
        holder.mRateView.text = t1
        t1 = "${item.stars} star rating"
        holder.mStarsView.text = t1
        Picasso.get().load(item.imageUrl).into(holder.mImageView)
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mDistanceView: TextView = mView.driver_distance_on_card
        val mNameView: TextView = mView.driver_name_card
        val mRateView: TextView = mView.driver_rate_on_card
        val mImageView: ImageView = mView.driver_image_card
        val mStarsView: TextView = mView.driver_stars_on_card

        override fun toString(): String {
            return super.toString() + " '" + mNameView.text + "'"
        }
    }


}
