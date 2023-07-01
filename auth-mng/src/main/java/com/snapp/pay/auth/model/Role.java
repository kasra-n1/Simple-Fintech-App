package com.snapp.pay.auth.model;

import com.snapp.pay.commons.model.JpaBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends JpaBaseEntity {

    @Serial
    private static final long serialVersionUID = -545230301507729852L;

    @NotBlank
    @Column(nullable = false)
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
