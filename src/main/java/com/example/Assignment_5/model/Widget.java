package com.example.Assignment_5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title;
    private String wtype;

    @ManyToOne
    @JsonIgnore
    private Topic topic;

    public Widget(int id,  String title, String wtype) {
        this.id = id;
        this.title = title;
        this.wtype = wtype;
    }
    
    public Widget() {}
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

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
