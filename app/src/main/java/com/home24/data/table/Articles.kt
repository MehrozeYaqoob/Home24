package com.home24.data.table

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


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
	@SerializedName("_links") val Links : Links
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