// HelloWorldController.java
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/greeting")
    public String helloWorld(@RequestParam String country) {
        switch(country.toLowerCase()) {
            case "usa":
                return "Hello, USA!";
            case "japan":
                return "こんにちは、日本!";
            case "france":
                return "Bonjour, France!";
            case "germany":
                return "Hallo, Deutschland!";
            case "italy":
                return "Ciao, Italia!";
            default:
                return "Hello, World!";
        }
    }
}
