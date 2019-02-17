package com.example.Assignment_5.model;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private int id;
    private String title;
    private List<Module> modules;
    private int authorId;


    public Course(int id, String title, int authorId ){
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        modules = new ArrayList<Module>();
    }

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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
