package com.maldaliza.springboard.dto;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {

    private Long id;
    private String title;
    private String author;
    private String content;

    public BoardDto(Long id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
