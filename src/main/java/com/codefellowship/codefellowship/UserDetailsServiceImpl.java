package com.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = this.userRepo.findByUsername(username);

        if (appUser == null) {
            System.out.println("User not found " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        System.out.println("Found USer: " + username);
        return appUser;
    }
}
