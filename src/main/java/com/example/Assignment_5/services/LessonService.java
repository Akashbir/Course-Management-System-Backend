package com.example.Assignment_5.services;

import com.example.Assignment_5.model.Course;
import com.example.Assignment_5.model.Lesson;
import com.example.Assignment_5.model.Module;
import com.example.Assignment_5.repositories.LessonRepository;
import com.example.Assignment_5.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class LessonService {


    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @PostMapping("/api/module/{mid}/lesson")
    public List<Lesson> createLesson(@PathVariable("mid") int moduleId, @RequestBody Lesson newLesson,
                                     HttpSession session) {

        Module module = moduleRepository.findById(moduleId).get();
        newLesson.setModule(module);
        lessonRepository.save(newLesson);
        return module.getLessons();

    }

    @GetMapping("/api/module/{mid}/lesson")
    public List<Lesson> findAllLessons(@PathVariable("mid") int moduleId, HttpSession session) {
        Module module = moduleRepository.findById(moduleId).get();
        return module.getLessons();
    }

    @GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(@PathVariable("lid") int lessonId, HttpSession session) {

        Lesson lesson = lessonRepository.findById(lessonId).get();
        return lesson;
    }

    @PutMapping("/api/lesson/{lid}")
    public Lesson updateLesson(@PathVariable("lid") int lessonId, @RequestBody Lesson updatedLesson,
                               HttpSession session) {

        Lesson lesson = lessonRepository.findById(lessonId).get();
        lesson.setTitle(updatedLesson.getTitle());
        return lessonRepository.save(lesson);
    }

    @DeleteMapping("/api/lesson/{lid}")
    public void deleteLesson(@PathVariable("lid") int lessonId, HttpSession session) {
            lessonRepository.deleteById(lessonId);

    }
}
