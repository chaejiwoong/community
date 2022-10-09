package chaeji.community.web.dto;

import chaeji.community.domain.Reply;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyResponseDto {

    private String comments;

    private LocalDateTime regDate;

    private String writer;

    public ReplyResponseDto(Reply reply) {
        this.comments = reply.getComments();
        this.regDate = reply.getLastModifiedDate();
        this.writer = reply.getMember().getName();
    }
}
