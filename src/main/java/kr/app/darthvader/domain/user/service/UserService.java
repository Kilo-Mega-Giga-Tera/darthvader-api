package kr.app.darthvader.domain.user.service;

import kr.app.darthvader.global.error.exception.UserMessageException;
import kr.app.darthvader.domain.user.repository.UserRepository;
import kr.app.darthvader.domain.user.model.dto.request.TuserRequestDto;
import kr.app.darthvader.domain.user.model.dto.response.TuserResponseDto;
import kr.app.darthvader.domain.user.model.entity.Tuser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public TuserResponseDto saveUser(TuserRequestDto dto) throws UserMessageException {
        Tuser tuser = new Tuser(dto.getName(), dto.getUserId(), dto.getPassword());

        int dup = userRepository.countByUserId(dto.getUserId());

        if (dup > 0) {
            throw new UserMessageException("이미 가입된 회원입니다");
        }

        Tuser save = userRepository.save(tuser);
        return new TuserResponseDto(save.getUserId(), save.getUserNm(), save.getRole());
    }

    public List<TuserResponseDto> selectUser() {
        return userRepository.selectUser();
    }

    public TuserResponseDto selectUserById(String userId) {

        int dup = userRepository.countByUserId(userId);

        if (dup == 0) {
            throw new UserMessageException("회원 정보가 없습니다");
        }

        return userRepository.selectUserById(userId);
    }

}
