package com.example.Assignment_5.services;


import com.example.Assignment_5.model.Course;
import com.example.Assignment_5.model.Module;
import com.example.Assignment_5.model.User;
import com.example.Assignment_5.repositories.CourseRepository;
import com.example.Assignment_5.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class ModuleService {


//    private List<Module> modules ;

        @Autowired
    CourseRepository courseRepository;

        @Autowired
    ModuleRepository moduleRepository;

        @PostMapping("/api/courses/{cid}/modules")
        public List<Module> createModule(@PathVariable("cid") Integer cid, @RequestBody Module newModule,
                                         HttpSession session){

                Course course = courseRepository.findById(cid).get();
                newModule.setCourse(course);
                moduleRepository.save(newModule);
                return course.getModules();
        }

        @GetMapping("/api/courses/{cid}/modules")
        public List<Module> findAllModules(@PathVariable("cid") int cid,HttpSession session){

            Course course = courseRepository.findById(cid).get();
            return course.getModules();

        }

        @GetMapping("/api/modules/{mid}")
        public Module findModuleById(@PathVariable("mid") Integer moduleId,HttpSession session){

            Module module = moduleRepository.findById(moduleId).get();
            return module;
        }

        @PutMapping("/api/modules/{mid}")
        public Module updateModule(@PathVariable("mid") Integer moduleId,@RequestBody Module updatedModule,
                                   HttpSession session){

            Module module = moduleRepository.findById(moduleId).get();
            module.setTitle(updatedModule.getTitle());
            return moduleRepository.save(module);

        }

        @DeleteMapping("/api/modules/{mid}")
        public void deleteModule(@PathVariable("mid") Integer moduleId,HttpSession session){
            moduleRepository.deleteById(moduleId);
        }
    }




