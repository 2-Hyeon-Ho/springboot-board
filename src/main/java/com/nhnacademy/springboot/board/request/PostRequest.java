package com.nhnacademy.springboot.board.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostRequest {

    @NotBlank
    @Size(min = 1)
    @Size(max = 30)
    private String title;

    @NotBlank
    @Size(min = 1)
    @Size(max = 300)
    private String content;
}
