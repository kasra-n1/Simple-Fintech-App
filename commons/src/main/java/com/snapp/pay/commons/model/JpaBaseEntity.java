package com.snapp.pay.commons.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@MappedSuperclass
public class JpaBaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -805708913863070526L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
