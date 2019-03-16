package com.example.Assignment_5.services;


import com.example.Assignment_5.model.ImageWidget;
import com.example.Assignment_5.model.ListWidget;
import com.example.Assignment_5.model.Topic;
import com.example.Assignment_5.model.Widget;
import com.example.Assignment_5.repositories.ImageWidgetRepository;
import com.example.Assignment_5.repositories.ListWidgetRepository;
import com.example.Assignment_5.repositories.TopicRepository;
import com.example.Assignment_5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class ImageWidgetService {
    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    ImageWidgetRepository imageWidgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/api/topic/{tid}/image/widget")
    public List<Widget> createWidget(@PathVariable("tid") int topicId, @RequestBody ImageWidget newWidget, HttpSession session){
        Topic topic = topicRepository.findById(topicId).get();
        newWidget.setTopic(topic);
        imageWidgetRepository.save(newWidget);
        return topic.getWidgets();

    }

    @GetMapping("/api/image/widget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int widgetId, HttpSession session){

        ImageWidget widget = imageWidgetRepository.findById(widgetId).get();
        return widget;

    }

    @PutMapping("/api/image/widget/{wid}")
    public Widget updateWidget(@PathVariable("wid") int widgetId, @RequestBody ImageWidget updatedWidget){
        ImageWidget widget = imageWidgetRepository.findById(widgetId).get();
        //   widget.setText(updatedWidget.getText());
//        return paragraphWidgetRepository.save(widget);
        return widgetRepository.save(widget);
    }
}
