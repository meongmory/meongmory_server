package com.meongmory.meongmory.domain.user.controller;

import com.meongmory.meongmory.domain.user.dto.request.SignUpUserReq;
import com.meongmory.meongmory.domain.user.dto.response.SignUpUserRes;
import com.meongmory.meongmory.domain.user.service.UserServiceImpl;
import com.meongmory.meongmory.global.payload.ErrorResponse;
import com.meongmory.meongmory.global.response.ResponseCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
  private final UserServiceImpl userService;

  @Operation(summary = "회원가입", description = "회원가입을 진행한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "회원가입 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SignUpUserRes.class))}),
          @ApiResponse(responseCode = "400", description = "회원가입 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
  })
  @PostMapping("/signUp")
  public ResponseCustom<SignUpUserRes> signUp(@RequestBody SignUpUserReq signUpUserReq
  ) {
    return ResponseCustom.OK(userService.signUp(signUpUserReq));
  }

}
