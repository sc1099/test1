package com.example.mylibrary.mypaging3.bean

import com.google.gson.annotations.SerializedName

class RepoResponse {

    @SerializedName("items") val items: List<Repo> = emptyList()
}