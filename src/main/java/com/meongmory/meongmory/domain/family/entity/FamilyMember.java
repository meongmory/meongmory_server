package com.meongmory.meongmory.domain.family.entity;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import com.meongmory.meongmory.domain.user.entity.User;
import com.meongmory.meongmory.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class FamilyMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyMemberId;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="familyId")
    private Family family;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId")
    private User user;


    @Builder
    public FamilyMember(MemberType type, Family family, User user) {
        this.type=type;
        this.family = family;
        this.user = user;
    }
}
