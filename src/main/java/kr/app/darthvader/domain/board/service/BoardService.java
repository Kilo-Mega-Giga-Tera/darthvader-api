package kr.app.darthvader.domain.board.service;

import kr.app.darthvader.domain.board.model.dto.request.BoardRequestDto;
import kr.app.darthvader.domain.board.model.dto.response.BoardDetailResponseDto;
import kr.app.darthvader.domain.board.model.dto.response.BoardListResponseDto;
import kr.app.darthvader.domain.board.model.dto.response.BoardResponseDto;
import kr.app.darthvader.domain.board.model.entity.Tboard;
import kr.app.darthvader.domain.board.repository.BoardRepository;
import kr.app.darthvader.domain.user.model.entity.Tuser;
import kr.app.darthvader.domain.user.repository.UserRepository;
import kr.app.darthvader.global.error.exception.UserMessageException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static kr.app.darthvader.global.security.filter.JWTUtils.getUsername;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public BoardResponseDto saveBoard(BoardRequestDto dto) {
        Optional<Tuser> user = userRepository.findByUserId(getUsername());

        user.orElseThrow(() -> new RuntimeException("등록된 회원이 아닙니다"));

        Tboard board = new Tboard(dto.getTitle(), dto.getContent(), user.get());
        Tboard savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard);
    }

    public List<BoardListResponseDto> selectBoard() {
        return boardRepository.selectBoard();
    }

    public BoardDetailResponseDto selectBoardDetail(Long seq) {
        BoardDetailResponseDto dto = boardRepository.selectBoardDetail(seq);

        if (ObjectUtils.isEmpty(dto)) {
            throw new UserMessageException("조회된 글이 없습니다.");
        }

        return dto;
    }

}
