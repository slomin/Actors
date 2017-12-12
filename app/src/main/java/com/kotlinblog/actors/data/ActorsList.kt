package com.kotlinblog.actors.data

import com.google.gson.annotations.SerializedName

class ActorsList {
    @SerializedName("actors") val actors: List<Actor> = emptyList()
}