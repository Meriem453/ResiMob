package com.example.residence_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.data.OnBoardingItemsData

class OnBoardingItemsAdapter(private val onBoardingItems : List<OnBoardingItemsData>) : RecyclerView.Adapter<OnBoardingItemsAdapter.OnBoardingItemsViewHolder>()  {
    inner class OnBoardingItemsViewHolder(view : View) : RecyclerView.ViewHolder(view){
     private val onBoardingImage = view.findViewById<ImageView>(R.id.OnBoardingImageID)
     private val onBoardingMainText = view.findViewById<TextView>(R.id.mainTextID)
     private val onBoardingSecondText = view.findViewById<TextView>(R.id.secondTextID)


        fun bind(onBoardingItemsData: OnBoardingItemsData){
            onBoardingImage.setImageResource(onBoardingItemsData.onBoardingImage)
            onBoardingMainText.text = onBoardingItemsData.mainText
            onBoardingSecondText.text = onBoardingItemsData.secondText

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemsViewHolder {
        return OnBoardingItemsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.onboardingitemscontainer,parent,false))
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    override fun onBindViewHolder(holder: OnBoardingItemsViewHolder, position: Int) {
        holder.bind(onBoardingItems[position])
    }
}