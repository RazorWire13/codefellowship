package com.codefellowship.codefellowship;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Post properties
    public long id;
    public String body;
    public Date createdOn;

    // Post Constructors
    public Post() {}
    public Post(String body, Date createdOn) {
        this.body = body;
        this.createdOn = createdOn;
    }

    // Define Relationships
    @ManyToOne
    public AppUser appUser;

    public Date getCreatedOn() {
        return createdOn;
    }

    // Data return as string
    public String toString() {
        return "Body: " + body + "\nDate: " + createdOn;
    }
}
