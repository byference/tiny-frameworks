package com.github.byference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * IndexController
 *
 * @author byference
 * @since 2019-11-17
 */
@Controller
public class Index1Controller {
    
    @GetMapping({"", "index"})
    public String index() {

        return "index.html";
    }

    @GetMapping("user")
    @ResponseBody
    public String user() {

        return "user";
    }

    @GetMapping("admin")
    @ResponseBody
    public String admin() {

        return "admin";
    }

}
