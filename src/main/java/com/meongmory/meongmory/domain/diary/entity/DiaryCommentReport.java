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
public class DiaryCommentReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryCommentReportId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="diaryCommentId")
    private DiaryComment diaryComment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private Reason reason;

    @Builder
    public DiaryCommentReport(DiaryComment diaryComment, User user, Reason reason) {
        this.diaryComment = diaryComment;
        this.user = user;
        this.reason=reason;
    }
}
