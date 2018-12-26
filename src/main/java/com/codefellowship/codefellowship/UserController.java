package com.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

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
    public String displayProfile(@PathVariable long userId, Model model) {
        model.addAttribute("user", userRepo.findById(userId).get());
        return "profile";
    }

    // Display user 'myprofile' page
    @RequestMapping(value="/myprofile", method= RequestMethod.GET)
    public String displayMyProfile(Principal p, Model model) {
        System.out.println(p);
        AppUser current = (AppUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        model.addAttribute("user", current);
        return "myprofile";
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

        // Auto login for users
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // redirect user back to their profile once created
        return new RedirectView("/myprofile");
    }

}

