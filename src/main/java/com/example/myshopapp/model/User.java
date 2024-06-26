package com.example.myshopapp.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname", length = 100)
    private String fullName;

    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "user_password", length = 100, nullable = false)
    private String password;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "facebook_account_id")
    private int facebookAccountId;

    @Column(name = "google_account_id")
    private int googleAccountId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    //    @Override
    //    public Collection<? extends GrantedAuthority> getAuthorities() {
    //        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    //        authorities.add(new SimpleGrantedAuthority("ROLE_" + getRole().getName().toUpperCase()));
    //        return authorities;
    //    }
    //
    //    @Override
    //    public String getUsername() {
    //        return phoneNumber;
    //    }
    //
    //    @Override
    //    public boolean isAccountNonExpired() {
    //        return true;
    //    }
    //
    //    @Override
    //    public boolean isAccountNonLocked() {
    //        return true;
    //    }
    //
    //    @Override
    //    public boolean isCredentialsNonExpired() {
    //        return true;
    //    }
    //
    //    @Override
    //    public boolean isEnabled() {
    //        return true;
    //    }
}
