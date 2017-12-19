package com.kotlinblog.actors.data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface Api {
    @GET("jsonActors")
    Single<ActorsList> getActors();
}
