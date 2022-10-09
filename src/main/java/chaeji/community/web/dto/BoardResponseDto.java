package chaeji.community.web.dto;

import chaeji.community.domain.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardResponseDto {

    private Long bno;

    private String title;

    private String content;

    private String writer;

    private String category;

    private LocalDateTime regDate;

    public BoardResponseDto(Board board) {
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getMember().getName();
        this.category = board.getCategory().getName();
        this.regDate = board.getLastModifiedDate();
    }
}
