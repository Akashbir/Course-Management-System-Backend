package com.example.Assignment_5.services;//package com.example.Assignment_5.services;
//
//import com.example.Assignment_5.model.*;
//import com.example.Assignment_5.repositories.WidgetRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//import java.awt.event.WindowFocusListener;
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
//public class WidgetService {
//
//    @Autowired
//    WidgetRepository widgetRepository;
//
//    @GetMapping("/api/widget/{wid}")
//    public Widget findWidgetById(@PathVariable("wid") int widgetId, HttpSession session){
//        List<Course> courses = CourseService.courses;
//        if (courses != null) {
//            for (Course course : courses) {
//                List<Module> modules = course.getModules();
//                for (Module module : modules) {
//                    List<Lesson> lessons = module.getLessons();
//                    for (Lesson lesson : lessons) {
//                        for(Topic topic: lesson.getTopics()){
//                            for(Widget widget: topic.getWidgets()){
//                                if(widget.getId()==widgetId){
//                                    return widget;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//
//    }
//}
