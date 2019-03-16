package com.example.Assignment_5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HeadingWidget extends Widget{

    private int size;

    private String text;


    public HeadingWidget(int id, String title, String type, int widgetOrder, int size, String text) {
        super(id, title, type, widgetOrder);
        this.size = size;
        this.text = text;
    }

    public HeadingWidget(){}




    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
