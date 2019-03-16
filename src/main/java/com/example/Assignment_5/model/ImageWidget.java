package com.example.Assignment_5.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImageWidget extends Widget {
    private String src;


    public ImageWidget(int id,  String title, String wtype, String src) {
        super(id,  title, wtype);
        this.src = src;
    }

    public ImageWidget(){}


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
