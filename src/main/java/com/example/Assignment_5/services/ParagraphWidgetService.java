package com.example.Assignment_5.services;


import com.example.Assignment_5.model.ParagraphWidget;
import com.example.Assignment_5.model.Topic;
import com.example.Assignment_5.model.Widget;
import com.example.Assignment_5.repositories.ParagraphWidgetRepository;
import com.example.Assignment_5.repositories.TopicRepository;
import com.example.Assignment_5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class ParagraphWidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    ParagraphWidgetRepository paragraphWidgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @PostMapping("/api/topic/{tid}/paragraph/widget")
    public Widget createWidget(@PathVariable("tid") int topicId, @RequestBody ParagraphWidget newWidget, HttpSession session){
        Topic topic = topicRepository.findById(topicId).get();
        newWidget.setTopic(topic);
        return widgetRepository.save(newWidget);

    }

    @GetMapping("/api/paragraph/widget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int widgetId, HttpSession session){

        ParagraphWidget widget = paragraphWidgetRepository.findById(widgetId).get();
        return widget;

    }

    @PutMapping("/api/paragraph/widget/{wid}")
    public Widget updateWidget(@PathVariable("wid") int widgetId, @RequestBody ParagraphWidget updatedWidget){
        ParagraphWidget widget = paragraphWidgetRepository.findById(widgetId).get();
        widget.setText(updatedWidget.getText());
        return paragraphWidgetRepository.save(widget);
    }

}
