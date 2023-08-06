package com.meongmory.meongmory.domain.notification.controller;

import com.meongmory.meongmory.domain.notification.dto.response.GetNotificationsRes;
import com.meongmory.meongmory.domain.notification.service.NotificationService;
import com.meongmory.meongmory.global.response.ResponseCustom;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "알림 API")
@RestController
@RequestMapping(value = "/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "알림 목록 조회 성공", content = @Content(schema = @Schema(implementation = GetNotificationsRes.class)))
    })
    @Operation(summary = "알림 목록 조회", description = "알림 전체 목록을 조회합니다.")
    public ResponseCustom<GetNotificationsRes> getNotifications() {
        // TODO 유저 토큰 받아오기
        return ResponseCustom.OK(notificationService.getNotifications(1L));
    }
}