package chaeji.community.web.dto;

import lombok.Data;

@Data
public class BoardDto {

    private Long bno;

    private String title;

    private String content;

    private Long cno;

    private Long mno;
}
