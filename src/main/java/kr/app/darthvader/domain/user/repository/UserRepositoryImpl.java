package kr.app.darthvader.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.darthvader.domain.user.model.dto.response.QTuserResponseDto;
import kr.app.darthvader.domain.user.model.dto.response.TuserResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static kr.app.darthvader.domain.user.model.entity.QTuser.tuser;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<TuserResponseDto> selectUser() {
        return query
                .select(new QTuserResponseDto(tuser.seq, tuser.userId,
                        tuser.userNm,
                        tuser.role))
                .from(tuser)
                .where(tuser.delYn.eq("N"))
                .limit(1000)
                .fetch();
    }

    @Override
    public TuserResponseDto selectUserById(String userId) {
        return query
                .select(new QTuserResponseDto(tuser.seq, tuser.userId,
                        tuser.userNm,
                        tuser.role))
                .from(tuser)
                .where(tuser.userId.eq(userId), tuser.delYn.eq("N"))
                .fetchOne();
    }

}
