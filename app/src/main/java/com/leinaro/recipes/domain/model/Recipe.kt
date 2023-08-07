package com.leinaro.recipes.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recipe (
    @SerializedName("id")
    var id: String,
    @SerializedName("recipe_name")
    var name: String,
    @SerializedName("instructions")
    var description: String,
    @SerializedName("image_url")
    var imageUrl: String,
    @SerializedName("ingredients")
    var ingredients: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("latitude")
    var latitude: Double,
    @SerializedName("longitude")
    var longitude: Double,
): Serializable
