package com.maldaliza.springboard.controller;

import com.maldaliza.springboard.domain.Board;
import com.maldaliza.springboard.dto.CreateBoardDto;
import com.maldaliza.springboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 목록 보기
     * @param model
     * @return
     */
    @GetMapping("/")
    public String list(Model model) {
        log.info("home");
        return "board/list";
    }

    /**
     * 글 쓰기 폼 (GET)
     * @return
     */
    @GetMapping("/post")
    public String createPostForm() {
        return "board/createPostForm";
    }

    /**
     * 글 쓰기 처리 (POST)
     * @param createBoardDto
     * @return
     */
    @PostMapping("/post")
    public String createPostProcess(@ModelAttribute CreateBoardDto createBoardDto) {

        // 1. DTO로 받아온 값들을 엔티티로 변환.
        Board board = new Board(createBoardDto.getTitle(), createBoardDto.getAuthor(), createBoardDto.getContent());
        System.out.println("board = " + board);
        System.out.println("createBoardDto = " + createBoardDto);

        // 2. 글 저장
        boardService.savePost(board);
        return "redirect:/";
    }
}
