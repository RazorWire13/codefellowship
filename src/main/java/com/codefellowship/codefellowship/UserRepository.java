package com.codefellowship.codefellowship;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
    }
