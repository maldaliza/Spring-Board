package com.maldaliza.springboard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardListDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime createdDate;

    public BoardListDto(Long id, String title, String author, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdDate = createdDate;
    }
}
