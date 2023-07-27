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
public class Diary_Comment_Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryCommentReport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="diaryCommentId")
    private Diary_Comment diaryCommentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId")
    private User userId;

    @Enumerated(EnumType.STRING)
    private Reason reason;

    @Builder
    public Diary_Comment_Report(Long diaryCommentReport, Diary_Comment diaryCommentId, User userId, Reason reason) {
        this.diaryCommentReport = diaryCommentReport;
        this.diaryCommentId = diaryCommentId;
        this.userId = userId;
        this.reason=reason;
    }
}
