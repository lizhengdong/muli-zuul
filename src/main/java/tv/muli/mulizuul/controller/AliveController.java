package tv.muli.mulizuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jory
 * @date 2019-03-20
 */
@RestController
public class AliveController {

    @Autowired
    private Environment environment;

    @RequestMapping("/alive")
    public String alive() {
        return "alive!";
    }

    @RequestMapping("/name")
    public String name() {
        return environment.getProperty("spring.application.name") + ":" + environment.getProperty("spring.profiles.active");
    }

    @RequestMapping("/")
    public String index() {
        return "muli zuul index.";
    }
}
