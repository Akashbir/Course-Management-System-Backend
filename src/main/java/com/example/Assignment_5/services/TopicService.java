package com.example.Assignment_5.services;

import com.example.Assignment_5.model.Course;
import com.example.Assignment_5.model.Lesson;
import com.example.Assignment_5.model.Module;
import com.example.Assignment_5.model.Topic;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class TopicService {

    private CourseService courseService = new CourseService();

    @PostMapping("/api/lesson/{lid}/topic")
    public List<Topic> createTopic(@PathVariable("lid") int lessonId, @RequestBody Topic topic,
                                   HttpSession session) {

        List<Course> courses = courseService.findAllCourses(session);
        if (courses != null) {
            for (Course course : courses) {
                List<Module> modules = course.getModules();
                for (Module module : modules) {
                    List<Lesson> lessons = module.getLessons();
                    for(Lesson lesson: lessons){
                        if(lesson.getId()==lessonId) {
                            List<Topic> topics = lesson.getTopics();
                            Random r = new Random();
                            topic.setId(r.nextInt(Integer.MAX_VALUE));
                            topics.add(topic);
                            return topics;
                        }
                    }
                }
            }
        }
        return null;

    }

    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopics(@PathVariable("lid") int lessonId, HttpSession session) {

//        List<Course> courses = courseService.findAllCourses(session);
        List<Course> courses = courseService.courses;

        for (Course course : courses) {
            List<Module> modules = course.getModules();
            for (Module module : modules) {
                List<Lesson> lessons = module.getLessons();
                for(Lesson lesson: lessons){
                    if(lesson.getId()==lessonId){
                        return lesson.getTopics();
                    }
                }
            }
        }
        return null;
    }

    @GetMapping("/api/topic/{tid}")
    public Topic findTopicById(@PathVariable("tid") int topicId, HttpSession session) {

        List<Course> courses = courseService.findAllCourses(session);
        if (courses != null) {
            for (Course course : courses) {
                List<Module> modules = course.getModules();
                for (Module module : modules) {
                    List<Lesson> lessons = module.getLessons();
                    for (Lesson lesson : lessons) {
                        for(Topic topic: lesson.getTopics()){
                            if(topic.getId()==topicId){
                                return topic;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @PutMapping("/api/topic/{tid}")
    public Topic updateTopic(@PathVariable("tid") int topicId, @RequestBody Topic updatedTopic,
                               HttpSession session) {

        List<Course> courses = courseService.findAllCourses(session);
        if (courses != null) {
            for (Course course : courses) {
                List<Module> modules = course.getModules();
                for (Module module : modules) {
                    List<Lesson> lessons = module.getLessons();
                    for(Lesson lesson: lessons){
                        List<Topic> topics = lesson.getTopics();
                        for(int i = 0 ; i< topics.size();i++){
                            if(topics.get(i).getId()==updatedTopic.getId()){
                                topics.set(i,updatedTopic);
                                return topics.get(i);
                            }
                        }
                    }

                }
            }
        }
        return null;
    }

    @DeleteMapping("/api/topic/{tid}")
    public List<Topic> deleteTopic(@PathVariable("tid") int topicId, HttpSession session) {
//        List<Course> courses = courseService.findAllCourses(session);
        List<Course> courses = courseService.courses;
        if (courses != null) {
            for (Course course : courses) {
                List<Module> modules = course.getModules();
                for (Module module : modules) {
                    List<Lesson> lessons = module.getLessons();
                    for(Lesson lesson: lessons){
                        List<Topic> topics = lesson.getTopics();
                        for(int i = 0 ; i< topics.size();i++){
                            if(topics.get(i).getId()==topicId){
                                topics.remove(i);
                                return topics;
                            }
                        }
                    }

                }
            }
        }
        return null;
    }
}
