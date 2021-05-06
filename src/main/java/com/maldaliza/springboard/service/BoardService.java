package com.maldaliza.springboard.service;

import com.maldaliza.springboard.domain.Board;
import com.maldaliza.springboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
