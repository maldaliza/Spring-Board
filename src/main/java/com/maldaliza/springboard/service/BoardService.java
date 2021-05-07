package com.maldaliza.springboard.service;

import com.maldaliza.springboard.domain.Board;
import com.maldaliza.springboard.dto.BoardDto;
import com.maldaliza.springboard.dto.BoardListDto;
import com.maldaliza.springboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 글 저장
     * @param board
     * @return
     */
    @Transactional
    public Long savePost(Board board) {
        Long savedId = boardRepository.save(board);
        return savedId;
    }

    /**
     * 글 목록 조회
     * @return
     */
    public List<BoardListDto> findBoardList() {

        // 1. Repository에서 글 전체 목록을 가져온다.
        List<Board> boardList = boardRepository.findAll();

        // 2. 엔티티 타입에서 DTO 타입으로 변환.
        List<BoardListDto> boardDtoList = boardList.stream()
                .map(board -> new BoardListDto(board.getId(), board.getTitle(), board.getAuthor(), board.getCreatedDate()))
                .collect(Collectors.toList());

        return boardDtoList;
    }

    /**
     * 글 상세보기 조회
     * @param id
     * @return
     */
    public BoardDto findBoard(Long id) {

        // 1. Repository에서 id에 해당하는 글을 가져온다.
        Board board = boardRepository.findOne(id);

        // 2. 엔티티 타입에서 DTO 타입으로 변환.
        BoardDto boardDto = new BoardDto(board.getId(), board.getTitle(), board.getAuthor(), board.getContent());

        return boardDto;
    }
}
