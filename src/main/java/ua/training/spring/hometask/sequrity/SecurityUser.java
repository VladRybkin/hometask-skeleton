package ua.training.spring.hometask.sequrity;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.training.spring.hometask.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public static UserDetails fromUser(User user) {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().name());
        Set<SimpleGrantedAuthority> grantedAuthorities = Sets.newHashSet(grantedAuthority);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                true,
                true,
                true,
                true,
                grantedAuthorities
        );
    }
}
