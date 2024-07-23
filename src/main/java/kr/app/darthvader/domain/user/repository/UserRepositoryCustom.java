package kr.app.darthvader.domain.user.repository;

import kr.app.darthvader.domain.user.model.dto.response.TuserResponseDto;

import java.util.List;

public interface UserRepositoryCustom {

    List<TuserResponseDto> selectUser();

    TuserResponseDto selectUserById(String userId);

}
