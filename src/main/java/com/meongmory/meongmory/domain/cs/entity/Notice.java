package com.meongmory.meongmory.domain.cs.entity;

import com.meongmory.meongmory.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;
    private String title;
    private String comment;

    @Builder
    public Notice(String title,String comment) {
        this.title=title;
        this.comment=comment;
    }
}
