package ro.fiipractic.springbootdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String getHelloFromDemo(){
        return "Hello de la Fii Practic!!";
    }
}
