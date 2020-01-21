package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.prefs.Preferences;

@RestController
public class WelcomeController {

    private String welcomeMessage;

    public WelcomeController(@Value("${welcome.message}") String welcomeMessage){
        this.welcomeMessage=welcomeMessage;
    }

    @GetMapping("/")
    public String sayHello() {
        System.out.println("this.welcomemessage :"+ this.welcomeMessage);

        return this.welcomeMessage;
    }
}
