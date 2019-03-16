package com.example.Assignment_5.services;

import com.example.Assignment_5.model.ListWidget;
import com.example.Assignment_5.model.ParagraphWidget;
import com.example.Assignment_5.model.Topic;
import com.example.Assignment_5.model.Widget;
import com.example.Assignment_5.repositories.ListWidgetRepository;
import com.example.Assignment_5.repositories.ParagraphWidgetRepository;
import com.example.Assignment_5.repositories.TopicRepository;
import com.example.Assignment_5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class ListWidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    ListWidgetRepository listWidgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @PostMapping("/api/topic/{tid}/list/widget")
    public List<Widget> createWidget(@PathVariable("tid") int topicId, @RequestBody ListWidget newWidget, HttpSession session){
        Topic topic = topicRepository.findById(topicId).get();
        newWidget.setTopic(topic);
        widgetRepository.save(newWidget);
        return topic.getWidgets();

    }

    @GetMapping("/api/list/widget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") int widgetId, HttpSession session){

        ListWidget widget = listWidgetRepository.findById(widgetId).get();
        return widget;

    }

    @PutMapping("/api/list/widget/{wid}")
    public Widget updateWidget(@PathVariable("wid") int widgetId, @RequestBody ListWidget updatedWidget){
        ListWidget widget = listWidgetRepository.findById(widgetId).get();
     //   widget.setText(updatedWidget.getText());
//        return paragraphWidgetRepository.save(widget);
        return widgetRepository.save(widget);
    }
}
