package kr.app.darthvader.domain.user.service;

import kr.app.darthvader.domain.user.model.dto.request.TuserLoginRequestDto;
import kr.app.darthvader.domain.user.model.dto.request.TuserRequestDto;
import kr.app.darthvader.domain.user.model.dto.response.TuserResponseDto;
import kr.app.darthvader.domain.user.model.entity.Tuser;
import kr.app.darthvader.domain.user.repository.UserRepository;
import kr.app.darthvader.global.error.exception.UserMessageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TuserResponseDto saveUser(TuserRequestDto dto) throws UserMessageException {
        Tuser tuser = new Tuser(dto.getName(), dto.getUserId(), passwordEncoder.encode(dto.getPassword()));

        int dup = userRepository.countByUserId(dto.getUserId());

        if (dup > 0) {
            throw new UserMessageException("이미 가입된 회원입니다");
        }

        Tuser save = userRepository.save(tuser);
        return new TuserResponseDto(save.getSeq(), save.getUserId(), save.getUserNm(), save.getRole());
    }

    public Tuser selectUser(TuserLoginRequestDto dto) {
        Tuser user = userRepository.findByUserId(dto.getUserId());

        if (ObjectUtils.isEmpty(user)) {
            return null;
        }

        return user;
    }

    public TuserResponseDto selectUserById(String userId) {

        int dup = userRepository.countByUserId(userId);

        if (dup == 0) {
            throw new UserMessageException("회원 정보가 없습니다");
        }

        return userRepository.selectUserById(userId);
    }

}
