package com.photon.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuzaoxin
 * @description 视图跳转控制器
 * @date 2023/09/12/ 12:26
 */
@RequestMapping("/api/view")
@Controller
public class ModelViewController {

    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/repush")
    public String repush(){
        return "repush1";
    }
}
