package com.meongmory.meongmory.domain.user.entity;

import com.meongmory.meongmory.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String nickname;
    private String phone;
    private Boolean marketing;

    @Enumerated(EnumType.STRING)
    private License license;

    @Builder
    public User(Long userId, String nickname, String phone, Boolean marketing, License license) {
        this.userId = userId;
        this.nickname=nickname;
        this.phone=phone;
        this.marketing=marketing;
        this.license=license;
    }
}
