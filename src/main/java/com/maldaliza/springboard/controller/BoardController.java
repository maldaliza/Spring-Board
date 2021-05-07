package com.maldaliza.springboard.controller;

import com.maldaliza.springboard.domain.Board;
import com.maldaliza.springboard.dto.BoardDto;
import com.maldaliza.springboard.dto.BoardListDto;
import com.maldaliza.springboard.dto.CreateBoardDto;
import com.maldaliza.springboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

        // 1. Service 계층의 findBoardList() 메소드를 이용해 DTO 타입의 리스트를 가져온다.
        List<BoardListDto> boardDtoList = boardService.findBoardList();

        // 2. DTO를 엔티티로 변환하지 않고, DTO 그대로 반환.
        model.addAttribute("postList", boardDtoList);

        return "board/list";
    }

    /**
     * 글 쓰기 폼 (GET)
     * @return
     */
    @GetMapping("/post")
    public String createPostForm() {
        log.info("create post form");
        return "board/createPostForm";
    }

    /**
     * 글 쓰기 처리 (POST)
     * @param createBoardDto
     * @return
     */
    @PostMapping("/post")
    public String createPostProcess(@ModelAttribute CreateBoardDto createBoardDto) {

        log.info("create post process");

        // 1. DTO로 받아온 값들을 엔티티로 변환. (그래야지 id값이 주어지게 된다.)
        Board board = new Board(createBoardDto.getTitle(), createBoardDto.getAuthor(), createBoardDto.getContent());
        System.out.println("board = " + board);
        System.out.println("createBoardDto = " + createBoardDto);

        // 2. 글 저장
        Long boardId = boardService.savePost(board);
        return "redirect:/detail/" + boardId;
    }

    /**
     * 글 상세 보기
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        log.info("detail");

        // 1. DTO 타입으로 id값에 해당하는 글을 가져온다.
        BoardDto boardDto = boardService.findBoard(id);

        // 2. View 반환. (엔티티로 변환하지 않고, DTO 그대로 반환)
        model.addAttribute("board", boardDto);

        return "board/detail";
    }
}
