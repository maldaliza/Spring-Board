package com.maldaliza.springboard.service;

import com.maldaliza.springboard.domain.Board;
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
     */
    @Transactional
    public void savePost(Board board) {
        boardRepository.save(board);
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
}
