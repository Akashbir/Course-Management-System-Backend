package com.example.Assignment_5.services;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import com.example.Assignment_5.model.Course;
import com.example.Assignment_5.model.User;
import com.example.Assignment_5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class UserService{

//    Course cs5610 = new Course(123, "CS5610");
//    Course cs4500 = new Course(234, "CS4500");
//
//    Course cs5010 = new Course(345,"CS5010");
//    Course cs1500 = new Course(456,"CS1500");

//    List<Course> coursesForAlice = new LinkedList<Course>() {{
//        add(cs5610);
//        add(cs4500);
//    }};
//
//    List<Course> coursesForBob = new LinkedList<Course>() {{
//        add(cs5010);
//        add(cs4500);
//    }};

    @Autowired
    UserRepository userRepository;

//    User alice = new User( 123, "alice","alice", "Alice","Wonderland", "Faculty","+18374765412","alice@gmail.com");
//    User bob = new User( 234, "bob", "bob", "Bob","Marley", "Student","+1615251522","bob@gmail.com");

    List<User> users = new ArrayList<User>();
//        add(alice);
//        add(bob);
//    }};


    @PostMapping("/api/login")
    public User login(
            @RequestBody User loginUser, HttpSession session
    ){
        List<User> users = (List<User>) userRepository.findAll();
        for(User user: users){
            if(user.getUsername().equals(loginUser.getUsername()) &&
                    user.getPassword().equals(loginUser.getPassword())){

                session.setAttribute("currentUser", user);
                return user;
            }
        }
        User newUser = new User();
        return newUser;
    }


    @PostMapping(path="/api/register",consumes="application/json",produces="application/json")
    public User register(
            @RequestBody User newUser, HttpSession session){

//        Random ran = new Random();
//        Integer id = ran.nextInt();

        for(User user : users){
            if(user.getUsername() == newUser.getUsername()){
                newUser.setUsername("");
                return newUser;
            }

        }

        if(newUser!=null){
            userRepository.save(newUser);
        }
        else {
            return null;
        }

        session.setAttribute("currentUser", newUser);
        return newUser;

    }



    @GetMapping("/api/profile")
    public User profile(HttpSession session){

        User loggedInUser =  (User)session.getAttribute("currentUser");
        loggedInUser = userRepository.findById(loggedInUser.getId()).get();
        return loggedInUser;

    }


    @PostMapping("/api/logout")
        public void logout( HttpSession session){
            session.invalidate();

        }



    @GetMapping("/api/user")
    public List<User> findAllUsers() {

        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(
            @PathVariable("userId") int id) {
        return userRepository.findById(id).get();
    }

    @PostMapping(path="/api/user/createUser",consumes="application/json",produces="application/json")
    public User createUser
            (@RequestBody User newUser) {
        users.add(newUser);
        for(User u: users) {
            if(u.equals(newUser)) {
                return u;
            }
        }
        return new User();
    }

//	@PostMapping("/api/user/createuser")
//	public User createUser
//	       (@RequestBody User newUser) {
//	   return newUser;
//	}


    @DeleteMapping("/api/user/deleteUser/{userId}")
    public void deleteUser(
            @PathVariable("userId") int id) {
       userRepository.deleteById(id);

    }



    @PutMapping(path="/api/user/updateUser/{userId}",consumes="application/json",produces="application/json")
    public User updateUser(
            @PathVariable("userId") int id, @RequestBody User newUser) {




        User user = userRepository.findById(id).get();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setPassword(newUser.getPassword());
        user.setUsername(newUser.getUsername());
        user.setRole(newUser.getRole());
        user.setEmail(newUser.getEmail());
        user.setPhoneNumber(newUser.getPhoneNumber());
        return userRepository.save(user);
//        for(User u: users) {
//            if(id == u.getId()) {
//                u.setId(user.getId());
//                u.setFirstName(user.getFirstName());
//                u.setLastName(user.getLastName());
//                u.setPassword(user.getPassword());
//                u.setUsername(user.getUsername());
//                u.setRole(user.getRole());
//                u.setEmail(user.getEmail());
//                u.setPhoneNumber(user.getPhoneNumber());
//                userRepository.save(u);
//                return u;
//
//            }
//
//        }
//        return null;

//        User user = userRepository.findById(id).get();
//        user.set(newUser);
//        return userRepository.save(user);}


//	@GetMapping(path="api/user/findUserBy/{query}")
//	public String findUser(
//			@PathVariable("query") String queryVariable,
//			@RequestParam("message") String msg
//			) {
//
//		return "Hello" + queryVariable + ", message = " + msg;
//
//
//
//
//	}
    }

    @GetMapping("/api/user/query")
    public List<User> searchUser(
            @RequestParam("username") String username,
            @RequestParam("password")String password,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("role") String role) {

        if(firstName.isEmpty() && lastName.isEmpty() && password.isEmpty() && username.isEmpty()) {
            return users;
        }

        List<User> searchList = new ArrayList<>();

        for(User aUser : users) {
            if(!firstName.equals("")
                    && !aUser.getFirstName().toUpperCase().startsWith(firstName.toUpperCase())) {
                continue;



            }

            if(!lastName.equals("")
                    && !aUser.getLastName().toUpperCase().startsWith(lastName.toUpperCase())) {
                continue;
            }

            if(!username.equals("")
                    && !aUser.getUsername().toUpperCase().startsWith(username.toUpperCase())) {
                continue;
            }

            if(!password.equals("")
                    && !aUser.getPassword().toUpperCase().startsWith(password.toUpperCase())) {
                continue;
            }

            if(!role.equals("")
                    && !aUser.getRole().toUpperCase().startsWith(role.toUpperCase())) {
                continue;
            }

            searchList.add(aUser);
        }

        return searchList;
    }













}