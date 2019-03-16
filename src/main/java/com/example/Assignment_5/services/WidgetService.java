package com.example.Assignment_5.services;//package com.example.Assignment_5.services;
//
import com.example.Assignment_5.model.*;
import com.example.Assignment_5.repositories.TopicRepository;
import com.example.Assignment_5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/api/widget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int widgetId, HttpSession session){

        Widget widget = widgetRepository.findById(widgetId).get();
        return widget;

    }

    @GetMapping("/api/topic/{tid}/widget")
    public List<Widget> findAllWidgets(@PathVariable("tid") int topicId, HttpSession session){
        Topic topic = topicRepository.findById(topicId).get();
        return topic.getWidgets();
    }

    @PostMapping("/api/topic/{tid}/widget")
    public List<Widget> createWidget(@PathVariable("tid") int topicId, @RequestBody Widget newWidget, HttpSession session){
        Topic topic = topicRepository.findById(topicId).get();
        newWidget.setTopic(topic);
        widgetRepository.save(newWidget);
        return topic.getWidgets();

    }

    @PutMapping("/api/widget/{wid}")
    public Widget updateWidget(@PathVariable("wid") int widgetId, @RequestBody Widget updatedWidget, HttpSession session){
        Widget widget = widgetRepository.findById(widgetId).get();
        widget.setTitle(updatedWidget.getTitle());
        widget.setWtype(updatedWidget.getWtype());
        return widgetRepository.save(widget);
    }

    @DeleteMapping("/api/widget/{wid}")
    public void deleteWidget(@PathVariable("wid") int widgetId, HttpSession session){
        widgetRepository.deleteById(widgetId);

    }
}
