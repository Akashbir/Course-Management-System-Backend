package com.example.Assignment_5.services;


import com.example.Assignment_5.model.*;

import com.example.Assignment_5.model.Course;
import com.example.Assignment_5.model.Module;
import com.example.Assignment_5.model.User;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class CourseService {

    static Course cs5610 = new Course(111, "Webdev",123);
    static Module cs5610M1 = new Module(1111,"React");
    static Module cs5610M2 = new Module(2222,"JQuery");
    private List<Module> cs5610Modules = new ArrayList<Module>() {{
        add(cs5610M1);
        add(cs5610M2);
    }};

    static Course cs5100 = new Course(222,"FAI",234);
    static Module cs5100M1 = new Module(3333,"Search");
    static Module cs5100M2 = new Module(4444,"ML");
    private List<Module> cs5100Modules = new ArrayList<Module>() {{
        add(cs5100M1);
        add(cs5100M2);
    }};

    {
        cs5610.setModules(cs5610Modules);
        cs5100.setModules(cs5100Modules);
    }

    static List<Course> courses = new ArrayList<Course>()
    {{
        add(cs5610);
        add(cs5100);
    }};


    static Lesson lesson1 = new Lesson(12345,"ReactLesson1");
    static Lesson lesson2 = new Lesson(23456,"ReactLesson2");
    private List<Lesson> lessons = new ArrayList<Lesson>(){{
        add(lesson1);
        add(lesson2);
    }};

    {
        cs5610M1.setLessons(lessons);
    }

    static Topic topic1 = new Topic(12345,"ReactTopic1");
    static Topic topic2 = new Topic(23456,"ReactTopic2");

    private List<Topic> topics = new ArrayList<Topic>(){{
        add(topic1);
        add(topic2);
    }};

    {
        lesson1.setTopics(topics);
    }





    @GetMapping("api/courses")
    public List <Course> findAllCourses (HttpSession session) {

        User user = (User) session.getAttribute("currentUser");
        System.out.println("user---->"+user.getId());
        int userId = user.getId();

        List<Course> returnList = new ArrayList<Course>();
        for (Course course : courses) {
            if (course.getAuthorId()==userId) {
                returnList.add(course);
            }
        }
        return returnList;
    }



    @PostMapping("/api/courses")
    public Course createCourse(
            @RequestBody Course course,
            HttpSession session

    ){

        User currentUser = (User)session.getAttribute("currentUser");
        Random ran = new Random();
        int id = ran.nextInt();
        course.setId(id);
        for (Course c : courses) {
            if (c.getId()==course.getId()) {
                return null;
            }
        }
//        System.out.println(currentUser.getId());
        course.setAuthorId(currentUser.getId());
        courses.add(course);
        return course;
    }

//    @GetMapping("/api/courses")
//    public List<Course> findAllCourses(HttpSession session)
//    {
//
////        User currentUser = (User)session.getAttribute("currentUser");
////        return currentUser.getCourses();
////        User currentUser = (User)session.getAttribute("currentUser");
////        if(currentUser!=null) {
////            for (Course course : this.courses) {
////                if (course.getAuthorId() == currentUser.getId()) {
////                    this.courses.add(course);
////                }
////            }
////        }
//
//
//        return this.courses;
//    }

//    @GetMapping("/api/courses")
//    public List<Course> findAllCourses(HttpSession session) {
//        List<Course> courses = new ArrayList<>();
//        User currentUser = (User) session.getAttribute("CurrentUser");
//
//        if (currentUser != null) {
//            for (Course course : this.courses) {
//                if (course.getAuthorId()==currentUser.getId()) {
//                    courses.add(course);
//                }
//            }
//        }
//
//        return courses;
//    }


    @GetMapping("/api/courses/{cid}")
    public Course findCourseById(
            @PathVariable("cid") int id,
            HttpSession session

    ) {
        User currentUser = (User) session.getAttribute("currentUser");
//        this.courses = currentUser.getCourses();


        for(Course course: courses){
            if(course.getId()==id && currentUser.getId()==course.getAuthorId()){
                return course;
            }
        }

        return null;
    }

    @PutMapping("/api/courses/{cid}")
    public Course updateCourse(@RequestBody  Course newCourse,
                               @PathVariable("cid") Integer id,
                               HttpSession session
                               ){

        User currentUser = (User) session.getAttribute("currentUser");
        for (Course c : courses) {
            if (c.getId()==newCourse.getId() && currentUser.getId()==c.getAuthorId()) {
                c.setTitle(newCourse.getTitle());
                return newCourse;
            }
        }
        return null;

    }

    @DeleteMapping("/api/courses/{cid}")
    public void deleteCourse(@PathVariable("cid") Integer id,
                             HttpSession session
                             ){

        User currentUser = (User) session.getAttribute("currentUser");

        for(Course c : courses){
            if(c.getId()==id){
                courses.remove(c);
                break;
            }
        }

        courses.removeIf(course -> (course.getId()==id ));



    }


}