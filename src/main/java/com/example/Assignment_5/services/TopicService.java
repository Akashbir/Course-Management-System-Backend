package com.example.Assignment_5.services;

import com.example.Assignment_5.model.Course;
import com.example.Assignment_5.model.Lesson;
import com.example.Assignment_5.model.Module;
import com.example.Assignment_5.model.Topic;
import com.example.Assignment_5.repositories.LessonRepository;
import com.example.Assignment_5.repositories.ModuleRepository;
import com.example.Assignment_5.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class TopicService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    TopicRepository topicRepository;

    @PostMapping("/api/lesson/{lid}/topic")
    public List<Topic> createTopic(@PathVariable("lid") int lessonId, @RequestBody Topic newTopic,
                                   HttpSession session) {

        Lesson lesson = lessonRepository.findById(lessonId).get();
        newTopic.setLesson(lesson);
        topicRepository.save(newTopic);
        return lesson.getTopics();

    }

    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopics(@PathVariable("lid") int lessonId, HttpSession session) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        return lesson.getTopics();
    }

    @GetMapping("/api/topic/{tid}")
    public Topic findTopicById(@PathVariable("tid") int topicId, HttpSession session) {
        Topic topic = topicRepository.findById(topicId).get();
        return topic;
    }

    @PutMapping("/api/topic/{tid}")
    public Topic updateTopic(@PathVariable("tid") int topicId, @RequestBody Topic updatedTopic,
                               HttpSession session) {
        Topic topic = topicRepository.findById(topicId).get();
        topic.setTitle(updatedTopic.getTitle());
        return topicRepository.save(topic);
    }

    @DeleteMapping("/api/topic/{tid}")
    public void deleteTopic(@PathVariable("tid") int topicId, HttpSession session) {

        topicRepository.deleteById(topicId);

    }
}
