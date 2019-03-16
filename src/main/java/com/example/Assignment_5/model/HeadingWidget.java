package com.example.Assignment_5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HeadingWidget extends Widget{

    private int size;



    public HeadingWidget(int id,  String title, String wtype, int size) {
        super(id,  title, wtype);
        this.size = size;
    }

    public HeadingWidget(){}




    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
