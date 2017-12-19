package com.kotlinblog.actors.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActorsList {
    @SerializedName("actors") public List<Actor> actors;
}
