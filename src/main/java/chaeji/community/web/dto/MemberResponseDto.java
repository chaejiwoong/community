package chaeji.community.web.dto;

import chaeji.community.domain.Member;
import lombok.Data;

@Data
public class MemberResponseDto {

    private Long mno;

    private String id;

    private String name;

    public MemberResponseDto(Member member) {
        this.mno = member.getMno();
        this.id = member.getMemberId();
        this.name = member.getName();
    }
}
