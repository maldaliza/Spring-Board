package com.maldaliza.springboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CreateBoardDto {

    private String title;
    private String author;
    private String content;
}
