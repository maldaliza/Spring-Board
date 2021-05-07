package com.maldaliza.springboard.repository.implementation;

import com.maldaliza.springboard.domain.Board;
import com.maldaliza.springboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class H2BoardRepository implements BoardRepository {

    private final EntityManager entityManager;

    /**
     * 글 저장
     * @param board
     */
    @Override
    public Long save(Board board) {
        if (board.getId() == null) {
            entityManager.persist(board);
        } else {
            entityManager.merge(board);
        }
        return board.getId();
    }

    /**
     * 글 단건 조회
     * @param id
     * @return
     */
    @Override
    public Board findOne(Long id) {
        Board findBoard = entityManager.find(Board.class, id);
        return findBoard;
    }

    /**
     * 글 다건 조회
     * @return
     */
    @Override
    public List<Board> findAll() {
        List<Board> boardList = entityManager.createQuery("select b from Board b", Board.class).getResultList();
        return boardList;
    }
}
