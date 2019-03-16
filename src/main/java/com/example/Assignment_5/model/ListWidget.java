package com.example.Assignment_5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class ListWidget extends Widget {
    private String items;
    private boolean ordered;


    public ListWidget(int id, String title, String type, int widgetOrder, String items, boolean ordered) {
        super(id, title, type, widgetOrder);
        this.items = items;
        this.ordered = ordered;
    }

    public ListWidget(){}

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }
}
