package com.meongmory.meongmory.domain.family.entity;

import com.meongmory.meongmory.domain.user.entity.User;
import com.meongmory.meongmory.global.entity.BaseEntity;
import com.meongmory.meongmory.global.entity.Type;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Family_Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyMemberId;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="familyId")
    private Family familyId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId")
    private User userId;

    @Builder
    public Family_Member(Long familyMemberId, Type type, Family familyId, User userId) {
        this.familyMemberId = familyMemberId;
        this.type=type;
        this.familyId = familyId;
        this.userId = userId;
    }
}
