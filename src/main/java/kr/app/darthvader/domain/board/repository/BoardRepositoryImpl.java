package kr.app.darthvader.domain.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.darthvader.domain.board.model.dto.BoardDetailDto;
import kr.app.darthvader.domain.board.model.dto.BoardListResponseDto;
import kr.app.darthvader.domain.board.model.dto.QBoardDetailDto;
import kr.app.darthvader.domain.board.model.dto.QBoardListResponseDto;
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
    public BoardDetailDto selectBoardDetail(Long seq) {
        return query
                .select(new QBoardDetailDto(tboard))
                .from(tboard)
                .where(tboard.id.eq(seq), tboard.delYn.eq("N"))
                .fetchOne();
    }

}
