package com.maldaliza.springboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class BoardController {

    /**
     * 목록 보기
     * @return
     */
    @GetMapping("/")
    public String list() {
        log.info("home");
        return "board/list";
    }
}
