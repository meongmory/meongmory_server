package com.meongmory.meongmory.domain.family.entity;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import com.meongmory.meongmory.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Family extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    private String name;

    private String familyInviteCode;

    private String friendInviteCode;

    @Builder
    public Family(String name, String familyInviteCode, String friendInviteCode) {
        this.name=name;
        this.familyInviteCode = familyInviteCode;
        this.friendInviteCode = friendInviteCode;
    }
}
