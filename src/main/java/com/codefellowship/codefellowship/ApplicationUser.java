package com.codefellowship.codefellowship;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApplicationUser {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

// User Properties
    public long id;
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String bio;

    // User Constructors
    public ApplicationUser() {}
    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    // Data return as string
    public String toString() {
        return "Username: " + username + "\nName: " + firstName + " " + lastName + "\nDOB: " + dateOfBirth + "\nStudent Bio: " + bio;
}
}
