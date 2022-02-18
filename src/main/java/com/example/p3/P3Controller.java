package com.example.p3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@Controller
public class P3Controller {

    @Autowired
    UserRepository repos;

    @GetMapping("/")
    public ModelAndView index(
            @ModelAttribute("formModel") User user,
            ModelAndView mav) {
        mav.setViewName("index");
        Iterable<User>list = repos.findAll();
        mav.addObject("data",list);
        return mav;
    }
    @PostMapping("/")
    @Transactional(readOnly=false)
    public ModelAndView form(
            @ModelAttribute("formModel") User user,
            ModelAndView mav){
        repos.saveAndFlush(user);
        return new ModelAndView("redirect:/");
    }
    @PostConstruct
    public void init(){
        User user1 = new User();
        user1.setName("ミッ◯ーマウス");
        repos.saveAndFlush(user1);

        user1 = new User();
        user1.setName("ド◯ルド");
        repos.saveAndFlush(user1);
    }

}
