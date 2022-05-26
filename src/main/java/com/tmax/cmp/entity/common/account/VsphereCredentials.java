package com.tmax.cmp.entity.common.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VsphereCredentials extends Credentials{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String server;
    private String username;
    private String password;
}
