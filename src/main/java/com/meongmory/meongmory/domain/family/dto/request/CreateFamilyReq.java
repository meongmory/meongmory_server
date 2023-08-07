package com.meongmory.meongmory.domain.family.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateFamilyReq {
    @NotBlank(message = "G0004")
    private String name;
}
