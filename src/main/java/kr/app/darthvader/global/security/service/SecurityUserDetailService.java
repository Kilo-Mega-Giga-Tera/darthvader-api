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

@Service
@RequiredArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Tuser user = userRepository.findByUserId(username);

        if (ObjectUtils.isEmpty(user)) {
            return null;
        }

        return new SecurityUserDetails(user);
    }

}
