package com.meongmory.meongmory.domain.diary.entity;

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
public class Diary_File extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryFileId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="diaryId")
    private Diary diaryId;

    private String fileKey;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Builder
    public Diary_File(Long diaryFileId, Diary diaryId, String fileKey, Type type) {
        this.diaryFileId = diaryFileId;
        this.diaryId = diaryId;
        this.fileKey = fileKey;
        this.type=type;
    }
}
