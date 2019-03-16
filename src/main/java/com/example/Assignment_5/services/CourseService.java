package com.example.Assignment_5.services;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.Assignment_5.model.*;

import com.example.Assignment_5.model.Course;
import com.example.Assignment_5.model.Module;
import com.example.Assignment_5.model.User;

import com.example.Assignment_5.repositories.CourseRepository;
import com.example.Assignment_5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.servlet.http.HttpSession;
import javax.xml.soap.SOAPPart;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    private List<Course> courses = new ArrayList<Course>();

//    static Course cs5610 = new Course(111, "Webdev",123);
//    static Module cs5610M1 = new Module(1111,"React");
//    static Module cs5610M2 = new Module(2222,"JQuery");
//    private List<Module> cs5610Modules = new ArrayList<Module>() {{
//        add(cs5610M1);
//        add(cs5610M2);
//    }};
//
//    static Course cs5100 = new Course(222,"FAI",234);
//
//
//    {
//        cs5610.setModules(cs5610Modules);
//        cs5100.setModules(cs5100Modules);
//    }
//
//    static List<Course> courses = new ArrayList<Course>()
//    {{
//        add(cs5610);
//        add(cs5100);
//    }};
//
//
//    static Lesson lesson1 = new Lesson(12345,"ReactLesson1");
//    static Lesson lesson2 = new Lesson(23456,"ReactLesson2");
//    private List<Lesson> lessons = new ArrayList<Lesson>(){{
//        add(lesson1);
//        add(lesson2);
//    }};
//
//    {
//        cs5610M1.setLessons(lessons);
//    }
//
//    static Topic topic1 = new Topic(12345,"ReactTopic1");
//    static Topic topic2 = new Topic(23456,"ReactTopic2");
//
//    private List<Topic> topics = new ArrayList<Topic>(){{
//        add(topic1);
//        add(topic2);
//    }};
//
//    {
//        lesson1.setTopics(topics);
//    }
//




    @GetMapping("/api/courses")
    public List <Course> findAllCourses (HttpSession session) {


        User user = (User) session.getAttribute("currentUser");
        user = userRepository.findById(user.getId()).get();
        System.out.println(user.getUsername());
        System.out.println(user.getAuthoredCourses());
        return user.getAuthoredCourses();

    }



    @PostMapping("/api/courses")
    public List<Course> createCourse(
            @RequestBody Course newCourse,
            HttpSession session

    ){



        User currentUser = (User) session.getAttribute("currentUser");
        currentUser = userRepository.findById(currentUser.getId()).get();


        this.courses = currentUser.getAuthoredCourses();
        this.courses.add(newCourse);
        currentUser.setAuthoredCourses(this.courses);


        newCourse.setAuthor(currentUser);
        courseRepository.save(newCourse);
        currentUser = userRepository.findById(currentUser.getId()).get();
        return currentUser.getAuthoredCourses();

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
        currentUser = userRepository.findById(currentUser.getId()).get();

        this.courses = currentUser.getAuthoredCourses();

        for(Course course: this.courses){
            if(course.getId() == id){
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
        currentUser = userRepository.findById(currentUser.getId()).get();

        this.courses = currentUser.getAuthoredCourses();

        for (Course c : this.courses) {
            if (c.getId()== id) {
                c.setTitle(newCourse.getTitle());
                userRepository.save(currentUser);
                return c;
            }
        }
        return null;

    }

    @DeleteMapping("/api/courses/{cid}")
    public void deleteCourse(@PathVariable("cid") Integer id,
                             HttpSession session
                             ){

        User currentUser = (User) session.getAttribute("currentUser");
        currentUser = userRepository.findById(currentUser.getId()).get();

        this.courses = currentUser.getAuthoredCourses();




        for(Course c : courses){
            if(c.getId()==id){
                courseRepository.delete(c);
                break;
            }
        }

//        courses.removeIf(course -> (course.getId()==id ));



    }


}