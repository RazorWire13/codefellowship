package com.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    // Display posts page
    @RequestMapping(value="/allposts", method= RequestMethod.GET)
    public String displayPosts(Principal p, Model model) {
        System.out.println(p);
//        Post current = (Post)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        model.addAttribute("user", ((UsernamePasswordAuthenticationToken) p).getPrincipal());
        return "posts";
    }

    // Display users posts
    @RequestMapping(value="/users/{userId}/post", method= RequestMethod.POST)
    public RedirectView displayPostsById(@PathVariable long userId,
                                         @RequestParam String comment) {
        AppUser user = userRepo.findById(userId).get();
        Post post = new Post(comment, new Date());
        post.appUser = user;
        postRepo.save(post);
        return new RedirectView("/users/{userId}");
    }

    @RequestMapping(value="/users/{userId}", method= RequestMethod.GET)
    public String getPost(@PathVariable long userId, Model model) {
        model.addAttribute("user", userRepo.findById(userId).get());
        return "posts";
    }

    @RequestMapping(value="/myposts", method= RequestMethod.GET)
    public String displayMyPosts(Principal p, Model model) {
        System.out.println(p);
        model.addAttribute("user",((UsernamePasswordAuthenticationToken) p).getPrincipal());
        return "posts";
    }

}
