package com.meongmory.meongmory.domain.family.controller;

import com.meongmory.meongmory.domain.family.dto.request.CreateFamilyReq;
import com.meongmory.meongmory.domain.family.service.FamilyService;
import com.meongmory.meongmory.global.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/families")
public class FamilyController {
    private final FamilyService familyService;

    @PostMapping("")
    @ResponseBody
    public ResponseCustom createFamily(@RequestBody @Valid CreateFamilyReq createFamilyReq){
        familyService.createFamily(createFamilyReq, 1L);
        return ResponseCustom.OK();
    }
}
