package kr.app.darthvader.domain.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.darthvader.domain.board.model.dto.response.BoardDetailResponseDto;
import kr.app.darthvader.domain.board.model.dto.response.BoardListResponseDto;
import kr.app.darthvader.domain.board.model.dto.response.QBoardDetailResponseDto;
import kr.app.darthvader.domain.board.model.dto.response.QBoardListResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static kr.app.darthvader.domain.board.model.entity.QTboard.tboard;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<BoardListResponseDto> selectBoard() {
        return query
                .select(new QBoardListResponseDto(tboard))
                .from(tboard)
                .where(tboard.delYn.eq("N"))
                .limit(1000)
                .fetch();
    }

    @Override
    public BoardDetailResponseDto selectBoardDetail(Long seq) {
        return query
                .select(new QBoardDetailResponseDto(tboard))
                .from(tboard)
                .where(tboard.id.eq(seq), tboard.delYn.eq("N"))
                .fetchOne();
    }

}
