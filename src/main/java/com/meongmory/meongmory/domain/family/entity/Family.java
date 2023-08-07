package com.meongmory.meongmory.domain.family.entity;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import com.meongmory.meongmory.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.meongmory.meongmory.global.util.Constant.Family.RANDOM_CODE_SIZE;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Family extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    private String name;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Diary> diaries = new ArrayList<>();

    private String familyInviteCode;

    private String friendInviteCode;

    @Builder
    public Family(String name, String familyInviteCode, String friendInviteCode) {
        this.name=name;
        this.familyInviteCode = familyInviteCode;
        this.friendInviteCode = friendInviteCode;
    }

    public static Family toEntity(String name) {
        return Family.builder()
                .name(name)
                .familyInviteCode(RandomStringUtils.random(RANDOM_CODE_SIZE, true, true))
                .friendInviteCode(RandomStringUtils.random(RANDOM_CODE_SIZE, true, true))
                .build();
    }
}
