package com.sparta.chapter031.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeCotroller {

    @GetMapping("/boardDetail")
    public String boardDetail() {
        return "boardDetail";

    }

    @GetMapping("/write")
    public String write() {
        return "write";

    }




}
