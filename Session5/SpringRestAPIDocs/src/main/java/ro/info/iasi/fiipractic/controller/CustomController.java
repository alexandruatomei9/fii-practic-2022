package ro.info.iasi.fiipractic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom")
public class CustomController {

    @GetMapping
    public String custom() {
        return "Response from the new controller !";
    }
}

