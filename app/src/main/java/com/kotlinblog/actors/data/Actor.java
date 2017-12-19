package com.kotlinblog.actors.data;

import com.google.gson.annotations.SerializedName;

public class Actor {
    @SerializedName("name") private String name;
    @SerializedName("description") private String description;
    @SerializedName("dob") private String dob;
    @SerializedName("country") private String country;
    @SerializedName("height") private String height;
    @SerializedName("spouse") private String spouse;
    @SerializedName("children") private String children;
    @SerializedName("image") private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dob='" + dob + '\'' +
                ", country='" + country + '\'' +
                ", height='" + height + '\'' +
                ", spouse='" + spouse + '\'' +
                ", children='" + children + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
