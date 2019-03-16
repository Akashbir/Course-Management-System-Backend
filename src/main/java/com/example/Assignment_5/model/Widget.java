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
    private String type;

    protected int widgetOrder;

    @ManyToOne
    @JsonIgnore
    private Topic topic;

    public Widget(int id,  String title, String type, int widgetOrder) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.widgetOrder = widgetOrder;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getWidgetOrder() {
        return widgetOrder;
    }

    public void setWidgetOrder(int widgetOrder) {
        this.widgetOrder = widgetOrder;
    }
}
