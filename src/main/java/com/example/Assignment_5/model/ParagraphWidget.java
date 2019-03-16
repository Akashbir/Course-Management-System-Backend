package com.example.Assignment_5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParagraphWidget extends Widget {
    private String text;


    public ParagraphWidget(int id, String title, String type, int widgetOrder, String text) {
        super(id, title, type, widgetOrder);
        this.text = text;
    }

    public ParagraphWidget(){}





    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
