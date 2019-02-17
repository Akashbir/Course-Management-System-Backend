package com.example.Assignment_5.model;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 44b3d282636d85f4e7b7febb612118b1090854c2
import java.util.List;

public class Lesson {
    private int id;
    private String title;
    private List<Topic> topics;


<<<<<<< HEAD
    public Lesson(int id , String title){
        this.id = id;
        this.title = title;
        topics = new ArrayList<Topic>();
    }

=======
>>>>>>> 44b3d282636d85f4e7b7febb612118b1090854c2
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
