package com.meongmory.meongmory.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BaseResponseCode {
    // success
    SUCCESS("S0001", HttpStatus.OK, "요청에 성공했습니다."),

    // cs
    INVALID_NOTICE_ID("C0001", HttpStatus.BAD_REQUEST, "존재하지 않는 공지사항입니다."),

    // token
    TOKEN_NOT_VALID("T0001", HttpStatus.BAD_REQUEST, "해당 토큰은 유효하지 않습니다."),
    TOKEN_EXPIRATION("T0002", HttpStatus.BAD_REQUEST, "토큰이 만료되었습니다."),

    // user
    USER_NOT_FOUND("U0001", HttpStatus.NOT_FOUND, "id에 해당하는 유저가 존재하지 않습니다."),
    ALREADY_PHONENUM("U0002", HttpStatus.BAD_REQUEST, "이미 존재하는 전화번호입니다."),

    // family
    FAMILY_NOT_FOUND("F0001", HttpStatus.NOT_FOUND, "id에 해당하는 가족이 존재하지 않습니다."),
    FAMILY_MEMBER_NOT_FOUND("F0002", HttpStatus.NOT_FOUND, "id에 해당하는 가족 구성원이 존재하지 않습니다."),

    // pet
    PET_NOT_FOUND("P0001", HttpStatus.NOT_FOUND, "id에 해당하는 반려동물이 존재하지 않습니다."),

    // diary
    SORT_TYPE_NOT_FOUND("D0001", HttpStatus.NOT_FOUND, "SortType을 찾을 수 없습니다."),
    DIARY_NOT_FOUND("D0002", HttpStatus.NOT_FOUND, "id에 해당하는 일기가 존재하지 않습니다."),
    INVALID_SCOPE("D0003", HttpStatus.FORBIDDEN, "해당 일기를 볼 수 있는 권한이 없습니다."),
    SCOPE_NOT_FOUND("D0004", HttpStatus.NOT_FOUND ,"존재하지 않는 다이어리 scope 입니다." ),
    FILETYPE_NOT_FOUND("D0005", HttpStatus.NOT_FOUND , "존재하지 않는 file type 입니다" );

    public final String code;
    public final HttpStatus status;
    public final String message;

}
