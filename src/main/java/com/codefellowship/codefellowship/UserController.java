package com.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Direct to splash page
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index() {
        return "index";
    }

    // Database display of user data
    @RequestMapping(value="/user-signup", method= RequestMethod.GET)
    public String index2(Model model) {
        //Display user info on screen
        model.addAttribute("users", userRepo.findAll());
        return "user-signup";
    }

    // Take in user information and add application user to the database
    @RequestMapping(value="/user-signup/", method=RequestMethod.POST)
    public RedirectView createUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String dateOfBirth,
            @RequestParam String bio) {
        // Season the password with some salt
        password = bCryptPasswordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(username, password, firstName, lastName, dateOfBirth, bio);
        userRepo.save(newUser);
        return new RedirectView("/");
    }

}

