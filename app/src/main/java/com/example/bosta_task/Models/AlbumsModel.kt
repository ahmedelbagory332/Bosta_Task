package com.example.bosta_task.Models

import com.squareup.moshi.Json


data class AlbumsModel (

    @Json(name = "userId" ) var userId : Int?    = null,
    @Json(name = "id"     ) var id     : Int?    = null,
    @Json(name = "title"  ) var title  : String? = null

)