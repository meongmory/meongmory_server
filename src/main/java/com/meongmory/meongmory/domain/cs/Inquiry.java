package com.meongmory.meongmory.domain.cs;

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
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId")
    private User userId;

    private String email;
    private String content;

    @Builder
    public Inquiry(Long inquryId, User userId, String email, String content){
        this.inquryId = inquryId;
        this.userId = userId;
        this.email=email;
        this.content=content;
    }
}
