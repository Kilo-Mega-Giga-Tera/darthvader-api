package kr.app.darthvader.global.security.service;

import kr.app.darthvader.domain.user.model.entity.Tuser;
import kr.app.darthvader.domain.user.repository.UserRepository;
import kr.app.darthvader.global.security.dto.SecurityUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Tuser> user = userRepository.findByUserId(username);
        return new SecurityUserDetails(user.orElseThrow(() -> new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다")));
    }

}
