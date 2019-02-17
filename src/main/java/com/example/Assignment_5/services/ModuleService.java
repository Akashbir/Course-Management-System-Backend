package com.example.Assignment_5.services;


import com.example.Assignment_5.model.Course;
import com.example.Assignment_5.model.Module;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class ModuleService {



        private CourseService courseService = new CourseService();

        @PostMapping("/api/courses/{cid}/modules")
        public List<Module> createModule(@PathVariable("cid") Integer cid, @RequestBody Module module,
                                         HttpSession session){

            Course course = courseService.findCourseById(cid,session);

            if(course!=null){
                List<Module> currentModules = course.getModules();
                Random r = new Random();
                module.setId(r.nextInt(Integer.MAX_VALUE));
                module.setLessons(new ArrayList<>());
                currentModules.add(module);
                course.setModules(currentModules);
                return currentModules;
            }

            return null;

        }

        @GetMapping("/api/courses/{cid}/modules")
        public List<Module> findAllModules(@PathVariable("cid") int cid,HttpSession session){

            Course course = courseService.findCourseById(cid,session);
            if(course!=null){
                return course.getModules();
            }
            return null;

        }

        @GetMapping("/api/modules/{mid}")
        public Module findModuleById(@PathVariable("mid") Integer moduleId,HttpSession session){

            List<Course> courses = courseService.findAllCourses(session);
            if(courses!=null){
                for(Course course:courses){
                    List<Module> modules = course.getModules();
                    for(Module module:modules){
                        if(module.getId()==moduleId){
                            return module;
                        }
                    }
                }
            }
            return null;
        }

        @PutMapping("/api/modules/{mid}")
        public Module updateModule(@PathVariable("mid") Integer moduleId,@RequestBody Module updatedModule,
                                   HttpSession session){

            List<Course> courses = courseService.findAllCourses(session);
            if(courses!=null){
                for(Course course:courses){
                    List<Module> modules = course.getModules();
                    for(int i=0;i<modules.size();i++){
                        if(modules.get(i).getId()==moduleId){
                            modules.set(i,updatedModule);
                            return modules.get(i);
                        }
                    }
                }
            }
            return null;
        }

        @DeleteMapping("/api/modules/{mid}")
        public List<Module> deleteModule(@PathVariable("mid") Integer moduleId,HttpSession session){

            List<Course> courses = courseService.findAllCourses(session);
            if(courses!=null){
                for(Course course:courses){
                    List<Module> modules = course.getModules();
                    for(int i=0;i<modules.size();i++){
                        if(modules.get(i).getId()==moduleId){
                            modules.remove(i);
                            return modules;
                        }
                    }
                }
            }
            return null;
        }
    }




