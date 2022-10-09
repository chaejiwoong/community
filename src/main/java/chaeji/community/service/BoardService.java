package chaeji.community.service;

import chaeji.community.domain.Board;
import chaeji.community.domain.Category;
import chaeji.community.domain.Member;
import chaeji.community.repository.BoardRepository;
import chaeji.community.repository.CategoryRepository;
import chaeji.community.repository.MemberRepository;
import chaeji.community.web.dto.BoardDto;
import chaeji.community.web.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public Long save(BoardDto dto) {

        Member member = memberRepository.findById(dto.getMno()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Category category = categoryRepository.findById(dto.getCno()).orElseThrow(() ->
                new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));

        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(category)
                .member(member)
                .build();

        return boardRepository.save(board).getBno();
    }

    public BoardResponseDto findOne(Long bno) {

        Board board = boardRepository.findById(bno).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll().stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(BoardDto dto) {
        Board board = boardRepository.findById(dto.getBno()).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        board.changeBoard(dto.getTitle(), dto.getContent());

        return board.getBno();
    }

    @Transactional
    public Long delete(Long bno) {
        boardRepository.deleteById(bno);

        return bno;
    }
}

