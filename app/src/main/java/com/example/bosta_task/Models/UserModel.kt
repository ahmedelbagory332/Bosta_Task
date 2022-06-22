package com.example.bosta_task.Models

import com.squareup.moshi.Json



data class UserModel (

    @Json(name ="id"      ) var id       : Int?     = null,
    @Json(name ="name"     ) var name     : String?  = null,
    @Json(name ="username" ) var username : String?  = null,
    @Json(name ="email"    ) var email    : String?  = null,
    @Json(name ="address"  ) var address  : Address? = Address(),
    @Json(name ="phone"    ) var phone    : String?  = null,
    @Json(name ="website"  ) var website  : String?  = null,
    @Json(name ="company"  ) var company  : Company? = Company()

    )

    data class Geo (

        @Json(name ="lat" ) var lat : String? = null,
        @Json(name ="lng" ) var lng : String? = null

    )
    data class Address (

        @Json(name ="street"  ) var street  : String? = null,
        @Json(name ="suite"   ) var suite   : String? = null,
        @Json(name ="city"    ) var city    : String? = null,
        @Json(name ="zipcode" ) var zipcode : String? = null,
        @Json(name ="geo"     ) var geo     : Geo?    = Geo()

    )

    data class Company (

        @Json(name ="name"        ) var name        : String? = null,
        @Json(name ="catchPhrase" ) var catchPhrase : String? = null,
        @Json(name ="bs"          ) var bs          : String? = null

    )

