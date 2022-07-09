package com.example.mylibrary.mypaging3.bean

import com.google.gson.annotations.SerializedName


// TODO: 先定义每一项数据，在定义每一页数据 
data class Repo(@SerializedName("id") val id: Int,
                @SerializedName("name") val name: String,
                @SerializedName("description") val description: String?,
                @SerializedName("stargazers_count") val starCount: Int) {

}