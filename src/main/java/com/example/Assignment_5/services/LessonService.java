package com.example.Assignment_5.services;

import com.example.Assignment_5.model.Course;
import com.example.Assignment_5.model.Lesson;
import com.example.Assignment_5.model.Module;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class LessonService {

    private CourseService courseService = new CourseService();

    @PostMapping("/api/module/{mid}/lesson")
    public List<Lesson> createLesson(@PathVariable("mid") int moduleId, @RequestBody Lesson lesson,
                                     HttpSession session) {

        List<Course> courses = courseService.findAllCourses(session);
        if (courses != null) {
            for (Course course : courses) {
                List<Module> modules = course.getModules();
                for (Module module : modules) {
                    if (module.getId()==moduleId) {
                        List<Lesson> lessons = module.getLessons();
                        Random r = new Random();
                        lesson.setId(r.nextInt(Integer.MAX_VALUE));
                        lesson.setTopics(new ArrayList<>());
                        lessons.add(lesson);
                        return lessons;
                    }
                }
            }
        }
        return null;

    }

    @GetMapping("/api/module/{mid}/lesson")
    public List<Lesson> findAllLessons(@PathVariable("mid") int moduleId, HttpSession session) {

        List<Course> courses = courseService.findAllCourses(session);

        for (Course course : courses) {
            List<Module> modules = course.getModules();
            for (Module module : modules) {
                if (module.getId()==moduleId) {
                    return module.getLessons();
                }
            }
        }
        return null;
    }

    @GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(@PathVariable("lid") int lessonId, HttpSession session) {

        List<Course> courses = courseService.findAllCourses(session);
        if (courses != null) {
            for (Course course : courses) {
                List<Module> modules = course.getModules();
                for (Module module : modules) {
                    for (Lesson lesson : module.getLessons()) {
                        if (lesson.getId()==lessonId) {
                            return lesson;
                        }
                    }
                }
            }
        }
        return null;
    }

    @PutMapping("/api/lesson/{lid}")
    public Lesson updateLesson(@PathVariable("lid") int lessonId, @RequestBody Lesson updatedLesson,
                               HttpSession session) {

        List<Course> courses = courseService.findAllCourses(session);
        if (courses != null) {
            for (Course course : courses) {
                List<Module> modules = course.getModules();
                for (Module module : modules) {
                    List<Lesson> lessons = module.getLessons();
                    for (int i = 0; i < lessons.size(); i++) {
                        if (lessons.get(i).getId()==updatedLesson.getId()) {
                            lessons.set(i, updatedLesson);
                            return lessons.get(i);
                        }
                    }
                }
            }
        }
        return null;
    }

    @DeleteMapping("/api/lesson/{lid}")
    public List<Lesson> deleteLesson(@PathVariable("lid") int lessonId, HttpSession session) {
        List<Course> courses = courseService.findAllCourses(session);
        if (courses != null) {
            for (Course course : courses) {
                List<Module> modules = course.getModules();
                for (Module module : modules) {
                    List<Lesson> lessons = module.getLessons();
                    for (int i = 0; i < lessons.size(); i++) {
                        if (lessons.get(i).getId()==lessonId) {
                            lessons.remove(i);
                            return lessons;
                        }
                    }
                }
            }
        }
        return null;
    }
}
