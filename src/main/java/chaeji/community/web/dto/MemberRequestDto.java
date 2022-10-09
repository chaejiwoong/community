package chaeji.community.web.dto;

import chaeji.community.domain.Member;
import lombok.Data;

@Data
public class MemberRequestDto {

    private Long mno;

    private String id;

    private String password;

    private String name;

}
