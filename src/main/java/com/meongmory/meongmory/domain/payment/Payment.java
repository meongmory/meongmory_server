package com.meongmory.meongmory.domain.payment;

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
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId")
    private User userId;

    @Builder
    public Payment(Long paymentId, User userId) {
        this.paymentId = paymentId;
        this.userId = userId;
    }
}
