package com.example.assignment1.data.model;

import android.media.session.MediaSession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class post {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("token")
    @Expose
    private String token;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "post{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", token=" + token +  '}';
    }
}

