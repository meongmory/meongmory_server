package com.meongmory.meongmory.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BaseResponseCode {
    // success
    SUCCESS("S0001", HttpStatus.OK, "요청에 성공했습니다."),

    // global
    BAD_REQUEST("G0001", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    EMAIL_NULL("G0002", HttpStatus.BAD_REQUEST, "이메일을 입력해 주세요."),
    EMAIL_INVALID("G0003", HttpStatus.BAD_REQUEST, "올바른 이메일 형식으로 입력해 주세요."),
    CONTENT_NULL("G0004", HttpStatus.BAD_REQUEST, "내용을 입력해 주세요."),
    TITLE_NULL("G0005", HttpStatus.BAD_REQUEST , "제목을 입력해주세요" ),

    // cs
    INVALID_NOTICE_ID("C0001", HttpStatus.BAD_REQUEST, "존재하지 않는 공지사항입니다."),

    // token
    TOKEN_NOT_VALID("T0001", HttpStatus.BAD_REQUEST, "해당 토큰은 유효하지 않습니다."),
    TOKEN_EXPIRATION("T0002", HttpStatus.BAD_REQUEST, "토큰이 만료되었습니다."),
    AUTH_ANNOTATION_NULL("T0003",HttpStatus.BAD_REQUEST,"토큰을 통해 userId를 추출하는 메서드에는 @Auth 어노테이션을 붙여주세요."),
    TOKEN_NULL("T0004", HttpStatus.BAD_REQUEST, "토큰을 입력해주세요."),



    // user
    USER_NOT_FOUND("U0001", HttpStatus.NOT_FOUND, "id에 해당하는 유저가 존재하지 않습니다."),
    ALREADY_PHONENUM("U0002", HttpStatus.BAD_REQUEST, "이미 존재하는 전화번호입니다."),
    NICKNAME_NOT_FOUND("U0003",HttpStatus.NOT_FOUND,"닉네임을 입력해주세요."),
    USER_NUMBER_NOT_FONUND("U0004",HttpStatus.NOT_FOUND,"해당 번호에 해당하는 유저가 존재하지 않습니다."),


    // family
    FAMILY_NOT_FOUND("F0001", HttpStatus.NOT_FOUND, "id에 해당하는 가족이 존재하지 않습니다."),
    FAMILY_MEMBER_NOT_FOUND("F0002", HttpStatus.NOT_FOUND, "id에 해당하는 가족 구성원이 존재하지 않습니다."),
    FAMILY_ALREADY_HAD("F0003", HttpStatus.BAD_REQUEST, "사용자의 가족 생성은 최대 1개입니다."),
    FAMILY_TYPE_ACCESS_DENIED("F0004", HttpStatus.BAD_REQUEST, "가족 기능에 접근이 가능한 사용자가 아닙니다."),
    FAMILY_OWNER_DELETE_DENIED("F0005", HttpStatus.BAD_REQUEST, "가족 생성자는 가족 구성원 삭제 불가능합니다."),
    FAMILY_MEMBER_CODE_INVALID("F0006",HttpStatus.BAD_REQUEST,"가족멤버 코드를 입력해주세요."),
    FAMILY_EXIST("F0007",HttpStatus.BAD_REQUEST,"이미 가족 구성원으로 포함된 유저입니다."),

    // pet
    PET_NOT_FOUND("P0001", HttpStatus.NOT_FOUND, "id에 해당하는 반려동물이 존재하지 않습니다."),
    PET_GENDER_NOT_FOUND("P0002", HttpStatus.NOT_FOUND, "반려동물의 성별 타입을 확인해주세요."),

    // animal
    ANIMAL_TYPE_NOT_FOUND("A0001", HttpStatus.BAD_REQUEST, "반려동물의 종류를 확인해주세요."),
    ANIMAL_NOT_FOUND("A0002", HttpStatus.NOT_FOUND, "반려동물 품종이 존재하지 않습니다."),

    // diary
    SORT_TYPE_NOT_FOUND("D0001", HttpStatus.NOT_FOUND, "SortType을 찾을 수 없습니다."),
    DIARY_NOT_FOUND("D0002", HttpStatus.NOT_FOUND, "id에 해당하는 일기가 존재하지 않습니다."),
    INVALID_SCOPE("D0003", HttpStatus.FORBIDDEN, "해당 일기를 볼 수 있는 권한이 없습니다."),
    SCOPE_NOT_FOUND("D0004", HttpStatus.NOT_FOUND ,"존재하지 않는 다이어리 scope 입니다." ),
    FILETYPE_NOT_FOUND("D0005", HttpStatus.NOT_FOUND , "존재하지 않는 file type 입니다" ),
    DIARY_PET_NOT_FOUND("D0006", HttpStatus.NOT_FOUND , "id에 해당하는 반려동물에 대한 기록이 존재하지 않습니다."),

    //sms
    SMS_NOT_FOUND("SM0001",HttpStatus.BAD_REQUEST,"전화번호를 입력해주세요."),
    SMS_NUMBER_NOT_FOUND("SM0002",HttpStatus.BAD_REQUEST,"인증번호를 입력해주세요."),
    SMS_EXPIRATION("SM0003",HttpStatus.BAD_REQUEST,"인증번호 유효시간이 지났거나 잘못 입력했습니다."),
    SMS_NUBER_INVALID("SM0004",HttpStatus.BAD_REQUEST,"전화번호를 확인해주세요."),
    SMS_CODE_INVALID("SM0003",HttpStatus.BAD_REQUEST,"인증번호를 확인해주세요.");




    public final String code;
    public final HttpStatus status;
    public final String message;

    public static BaseResponseCode findByCode(String code) {
        return Arrays.stream(BaseResponseCode.values())
                .filter(b -> b.getCode().equals(code))
                .findAny().orElseThrow(() -> new BaseException(BAD_REQUEST));
    }

}
