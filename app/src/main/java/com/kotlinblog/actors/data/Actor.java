package com.kotlinblog.actors.data;

import com.google.gson.annotations.SerializedName;

public class Actor {
    @SerializedName("name") public String name;
    @SerializedName("description") public String description;
    @SerializedName("dob") public String dob;
    @SerializedName("country") public String country;
    @SerializedName("height") public String height;
    @SerializedName("image") public String image;
}
