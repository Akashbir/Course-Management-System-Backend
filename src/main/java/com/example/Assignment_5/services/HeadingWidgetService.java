package com.example.Assignment_5.services;


import com.example.Assignment_5.model.HeadingWidget;
import com.example.Assignment_5.model.ListWidget;
import com.example.Assignment_5.model.Topic;
import com.example.Assignment_5.model.Widget;
import com.example.Assignment_5.repositories.HeadingWidgetRepository;
import com.example.Assignment_5.repositories.ListWidgetRepository;
import com.example.Assignment_5.repositories.TopicRepository;
import com.example.Assignment_5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class HeadingWidgetService {
    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    HeadingWidgetRepository headingWidgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @PostMapping("/api/topic/{tid}/heading/widget")
    public List<Widget> createWidget(@PathVariable("tid") int topicId, @RequestBody HeadingWidget newWidget, HttpSession session){
        Topic topic = topicRepository.findById(topicId).get();
        newWidget.setTopic(topic);
        widgetRepository.save(newWidget);
        return topic.getWidgets();

    }

    @GetMapping("/api/heading/widget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int widgetId, HttpSession session){

        HeadingWidget widget = headingWidgetRepository.findById(widgetId).get();
        return widget;

    }

    @PutMapping("/api/heading/widget/{wid}")
    public Widget updateWidget(@PathVariable("wid") int widgetId, @RequestBody HeadingWidget updatedWidget){
        HeadingWidget widget = headingWidgetRepository.findById(widgetId).get();
        //   widget.setText(updatedWidget.getText());
//        return paragraphWidgetRepository.save(widget);
        widget.setSize(updatedWidget.getSize());
        return widgetRepository.save(widget);
    }
}
