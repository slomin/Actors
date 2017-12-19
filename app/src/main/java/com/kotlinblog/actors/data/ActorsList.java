package com.kotlinblog.actors.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActorsList {
    @SerializedName("actors") private List<Actor> actors;

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "ActorsList{" +
                "actors=" + actors +
                '}';
    }
}
