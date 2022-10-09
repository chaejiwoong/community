package chaeji.community.service;

import chaeji.community.domain.Member;
import chaeji.community.repository.MemberRepository;
import chaeji.community.web.dto.MemberRequestDto;
import chaeji.community.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(MemberRequestDto dto) {

        Member member = Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .build();

        return memberRepository.save(member).getMno();
    }

    public MemberResponseDto findOne(Long mno) {
        Member member = memberRepository.findById(mno).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        return new MemberResponseDto(member);
    }

    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(MemberRequestDto dto) {
        Member member = memberRepository.findById(dto.getMno()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        member.changeName(dto.getName());

        return member.getMno();
    }

    @Transactional
    public Long delete(Long mno) {
        memberRepository.deleteById(mno);

        return mno;
    }
}
