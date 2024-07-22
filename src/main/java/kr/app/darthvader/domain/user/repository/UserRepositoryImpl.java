package kr.app.darthvader.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.darthvader.domain.user.model.dto.QTuserResponseDto;
import kr.app.darthvader.domain.user.model.dto.TuserResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static kr.app.darthvader.domain.user.model.entity.QTuser.tuser;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<TuserResponseDto> selectUser() {
        return query
                .select(new QTuserResponseDto(tuser.userId,
                        tuser.userNm,
                        tuser.role))
                .from(tuser)
                .limit(1000)
                .fetch();
    }

    @Override
    public TuserResponseDto selectUserById(String userId) {
        return query
                .select(new QTuserResponseDto(tuser.userId,
                        tuser.userNm,
                        tuser.role))
                .from(tuser)
                .where(tuser.userId.eq(userId))
                .fetchOne();
    }

}
