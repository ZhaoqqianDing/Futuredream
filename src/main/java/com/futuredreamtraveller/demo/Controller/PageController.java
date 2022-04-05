package com.futuredreamtraveller.demo.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/PageController")
@Slf4j
@Controller
public class PageController {
    @RequestMapping("/toIndex")
    public String index(){
        log.info("enter into index");
        return "index";
    }
    @RequestMapping("/toGame")
    public String game(){
        log.info("enter into game");
        return "game";
    }

    @RequestMapping("/toBlog")
    public String blog(){
        log.info("enter into blog");
        return "blog";
    }

    @RequestMapping("/toCalculator")
    public String calculator(){
        log.info("enter into calculator");
        return "calculator";
    }

}
