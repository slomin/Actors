package com.kotlinblog.actors.data

import io.reactivex.Single
import retrofit2.http.GET


interface Api {
    @GET("jsonActors")
    fun getActors(): Single<ActorsList>
}