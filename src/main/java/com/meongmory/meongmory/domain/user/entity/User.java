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
    private String image;

    private Boolean loginStatus;

    @Enumerated(EnumType.STRING)
    private License license;

    private String deviceToken;

    @Builder
    public User(String nickname, String phone, Boolean marketing, License license,String image) {
        this.nickname=nickname;
        this.phone=phone;
        this.marketing=marketing;
        this.license=license;
        this.image=image;
    }

    public void login() {
        this.loginStatus = true;
    }
    public void modifyMyPageNickName(String nickname) {this.nickname = nickname;
    }
}
