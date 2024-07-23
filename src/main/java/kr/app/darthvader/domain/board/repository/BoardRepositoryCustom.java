package kr.app.darthvader.domain.board.repository;

import kr.app.darthvader.domain.board.model.dto.BoardDetailDto;
import kr.app.darthvader.domain.board.model.dto.BoardListResponseDto;

import java.util.List;

public interface BoardRepositoryCustom {

    List<BoardListResponseDto> selectBoard();

    BoardDetailDto selectBoardDetail(Long seq);

}
