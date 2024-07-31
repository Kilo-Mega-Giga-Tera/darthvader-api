package kr.app.darthvader.global.security.dto;

import kr.app.darthvader.domain.user.model.entity.Tuser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class SecurityUserDetails implements UserDetails {

    private final Tuser tuser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) tuser::getRole);
        return collection;
    }

    @Override
    public String getPassword() {
        return tuser.getPassword();
    }

    @Override
    public String getUsername() {
        return tuser.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
