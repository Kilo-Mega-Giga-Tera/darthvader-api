package kr.app.darthvader.domain.board.repository;

import kr.app.darthvader.domain.board.model.dto.response.BoardDetailResponseDto;
import kr.app.darthvader.domain.board.model.dto.response.BoardListResponseDto;

import java.util.List;

public interface BoardRepositoryCustom {

    List<BoardListResponseDto> selectBoard();

    BoardDetailResponseDto selectBoardDetail(Long seq);

}
