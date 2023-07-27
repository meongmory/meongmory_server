package com.meongmory.meongmory.domain.diary.entity;

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
public class Diary_Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryCommentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="diaryId")
    private Diary diaryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId")
    private User userId;

    private String comment;

    @Builder
    public Diary_Comment(Long diaryCommentId, Diary diaryId, User userId, String comment) {
        this.diaryCommentId = diaryCommentId;
        this.diaryId = diaryId;
        this.userId = userId;
        this.comment=comment;
    }
}
