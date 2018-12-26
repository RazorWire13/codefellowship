package com.codefellowship.codefellowship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodefellowshipApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHomeRout() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/", String.class);
        assertEquals("The response code should be 200", 200, response.getStatusCodeValue());
        assertTrue("Response contains the header 'Code Fellowship'", response.toString().contains("<h1>Code Fellowship</h1>"));
    }

    @Test
    public void testSignUpRout() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/signup", String.class);
        assertEquals("The server should give back a 200 response", 200, response.getStatusCodeValue());
        assertTrue("Should respond with a from that makes a post to /signup within the page", response.toString().contains("<form action=\"/signup\" method=\"POST\">"));

    }

    @Test
    public void testLoginRout() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/login", String.class);
        assertEquals("The server should give back a 200 response", 200, response.getStatusCodeValue());
        assertTrue("Should respond with a form that makes a post to /perform_login within the page", response.toString().contains("<form action=\"/perform_login\" method=\"POST\">"));
    }

}

