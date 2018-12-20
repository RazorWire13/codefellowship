package com.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Display home page
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String displayHome() {
        return "index";
    }

    // Display signup page
    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String displaySignup() {
        return "signup";
    }

    // Display login page
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String displayLogin() {
        return "login";
    }

    // Display user profile page
    @RequestMapping(value="/profile/{userId}", method= RequestMethod.GET)
    public String displayMyProfile(@PathVariable long userId, Model model) {
        model.addAttribute("user", userRepo.findById(userId).get());
        return "profile";
    }

    // Take in user information and add user to the database
    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public RedirectView userSignup(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String dateOfBirth,
            @RequestParam String bio) {

        // Season the password with some salt
        password = bCryptPasswordEncoder.encode(password);
        AppUser newUser = new AppUser(username, password, firstName, lastName, dateOfBirth, bio);
        userRepo.save(newUser);
        // redirect user back to homepage
        return new RedirectView("/profile/" + newUser.id);
    }

}

