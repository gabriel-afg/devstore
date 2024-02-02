package com.dev.backend.devstore.domain.user;

import com.dev.backend.devstore.domain.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;

    private String verificationCode;

    private boolean enabled;

    private UserRole role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Transaction> transactions = new HashSet<>();

    public User (UserRequestDTO data){
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.address = data.address();
        this.phone = data.phone();
        this.verificationCode = data.verificationCode();
        this.role = data.role();
    }

    public void updateUser(UpdateUserDTO data){
        if (data.name() != null){
            this.name = data.name();
        }
        if (data.address() != null){
            this.address = data.address();
        }
        if (data.phone() != null){
            this.phone = data.phone();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
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
        return this.enabled;
    }
}
