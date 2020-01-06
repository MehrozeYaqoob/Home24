package com.home24.data.table

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName


data class Articles (

	@SerializedName("sku") val sku : Int,
	@SerializedName("title") val title : String,
	@SerializedName("description") val description : String,
	@SerializedName("prevPrice") val prevPrice : Price,
	@SerializedName("manufacturePrice") val manufacturePrice : String,
	@SerializedName("price") val price : Price,
	@SerializedName("delivery") val delivery : Delivery,
	@SerializedName("brand") val brand : Brand,
	@SerializedName("media") val media : List<Media>,
	@SerializedName("assemblyService") val assemblyService : String,
	@SerializedName("availability") val availability : String,
	@SerializedName("url") val url : String,
	@SerializedName("energyClass") val energyClass : String,
	@SerializedName("discount") val discount : Int,
	@SerializedName("reviews") val reviews : Reviews,
	@SerializedName("availableForAR") val availableForAR : Boolean,
	@SerializedName("labels") val labels : List<Label>,
	@SerializedName("showrooms") val showrooms : List<Showrooms>,
	@SerializedName("_metadata") val Metadata : Metadata,
	@SerializedName("_links") val Links : Links,
	@Transient var isLiked : Boolean = false
){
	companion object {
		val DIFF_CALLBACK: DiffUtil.ItemCallback<Articles> =
			object : DiffUtil.ItemCallback<Articles>() {
				override fun areItemsTheSame(@NonNull oldItem: Articles, @NonNull newItem: Articles): Boolean {
					return oldItem.sku == newItem.sku
				}

				override fun areContentsTheSame(@NonNull oldItem: Articles, @NonNull newItem: Articles): Boolean {
					return true
				}
			}
	}

	fun getArticleURL(): String{
		return if(media.isNotEmpty())
			media[0].uri
		else
			""
	}
}