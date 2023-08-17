package com.meongmory.meongmory.domain.family.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InviteFamilyMemberReq {
    @Schema(type = "String", description = "가족 코드", example = "nl2BpqB", required = true)
    @NotBlank(message = "G0004")
    private String familyCode;
}
