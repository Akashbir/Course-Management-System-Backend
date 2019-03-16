package com.example.Assignment_5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String title;

   @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Module> modules;

    @JsonIgnore
    @ManyToOne
    private User author;



    public Course(int id, String title, List<Module> modules){
        this.id = id;
        this.title = title;
        this.modules = modules;
    }

    public Course(){}

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
