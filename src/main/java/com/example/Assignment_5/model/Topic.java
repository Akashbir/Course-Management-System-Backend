package com.example.Assignment_5.model;

import java.util.List;

public class Topic {
    private int id;
    private String title;

    public Topic(int id, String title){
        this.id = id;
        this.title = title;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
