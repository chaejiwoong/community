package chaeji.community.service;

import chaeji.community.domain.Board;
import chaeji.community.domain.Member;
import chaeji.community.domain.Reply;
import chaeji.community.repository.BoardRepository;
import chaeji.community.repository.MemberRepository;
import chaeji.community.repository.ReplyRepository;
import chaeji.community.web.dto.ReplyDto;
import chaeji.community.web.dto.ReplyResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReplyService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;

    public Long save(ReplyDto dto) {
        Member member = memberRepository.findById(dto.getMno()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Board board = boardRepository.findById(dto.getBno()).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글 존재하지 않습니다."));


        Reply reply = Reply.builder()
                .comments(dto.getComments())
                .board(board)
                .member(member)
                .build();

        return replyRepository.save(reply).getRno();
    }

    public ReplyResponseDto findOne(Long rno) {
        Reply reply = replyRepository.findById(rno).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        return new ReplyResponseDto(reply);
    }

    public List<ReplyResponseDto> findAll() {
        return replyRepository.findAll().stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(ReplyDto dto) {
        Reply reply = replyRepository.findById(dto.getRno()).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        reply.changeComments(reply.getComments());

        return reply.getRno();
    }

    @Transactional
    public Long delete(Long rno) {
        replyRepository.deleteById(rno);

        return rno;
    }
}
