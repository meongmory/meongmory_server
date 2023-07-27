package com.meongmory.meongmory.domain.notification;

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
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="diaryId")
    private Diary diaryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId")
    private User userId;

    private String title;
    private String content;

    @Builder
    public Notification(Long notificationId, Diary diaryId, User userId, String title, String content) {
        this.notificationId = notificationId;
        this.diaryId = diaryId;
        this.userId = userId;
        this.title=title;
        this.content=content;
    }
}
