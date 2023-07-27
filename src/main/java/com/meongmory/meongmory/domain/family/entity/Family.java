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


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="diaryId")
    private Diary diaryId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Family_invite_code familyInviteCode;

    @Enumerated(EnumType.STRING)
    private Friend_invite_code friendInviteCode;

    @Builder
    public Family(Long familyId, Diary diaryId, String name, Family_invite_code familyInviteCode, Friend_invite_code friendInviteCode) {
        this.familyId = familyId;
        this.diaryId = diaryId;
        this.name=name;
        this.familyInviteCode = familyInviteCode;
        this.friendInviteCode = friendInviteCode;
    }
}
