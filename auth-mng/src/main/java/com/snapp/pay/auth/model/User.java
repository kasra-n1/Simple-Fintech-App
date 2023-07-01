package com.snapp.pay.auth.model;

import com.snapp.pay.commons.model.JpaBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends JpaBaseEntity {
    @Serial
    private static final long serialVersionUID = 9147304262683573000L;

    private String firstName;

    private String lastName;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Role role;

}
