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
public class Diary_Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryReportId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="diaryId")
    private Diary diaryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId")
    private User userId;

    @Enumerated(EnumType.STRING)
    private Reason reason;

    @Builder
    public Diary_Report(Long diaryReportId, Diary diaryId, User userId, Reason reason) {
        this.diaryReportId = diaryReportId;
        this.diaryId = diaryId;
        this.userId = userId;
        this.reason=reason;
    }
}
