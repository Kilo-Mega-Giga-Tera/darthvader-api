package kr.app.darthvader.domain.board.controller;

import jakarta.validation.Valid;
import kr.app.darthvader.domain.board.model.dto.response.BoardDetailResponseDto;
import kr.app.darthvader.domain.board.model.dto.response.BoardListResponseDto;
import kr.app.darthvader.domain.board.model.dto.request.BoardRequestDto;
import kr.app.darthvader.domain.board.model.dto.response.BoardResponseDto;
import kr.app.darthvader.domain.board.service.BoardService;
import kr.app.darthvader.domain.common.model.dto.RequestResult;
import kr.app.darthvader.domain.common.model.dto.ResponseListResult;
import kr.app.darthvader.domain.common.model.dto.ResponseMapResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public RequestResult<BoardResponseDto> saveBoard(@RequestBody @Valid BoardRequestDto dto) {
        return new RequestResult<>(boardService.saveBoard(dto));
    }

    @GetMapping("/board")
    public ResponseListResult<List<BoardListResponseDto>> selectBoard() {
        List<BoardListResponseDto> boardListResponseDtoList = boardService.selectBoard();
        return new ResponseListResult<>(boardListResponseDtoList, boardListResponseDtoList.size());
    }

    @GetMapping("/board/{seq}")
    public ResponseMapResult<BoardDetailResponseDto> selectBoardDetail(@PathVariable Long seq) {
        return new ResponseMapResult<>(boardService.selectBoardDetail(seq));
    }

}
