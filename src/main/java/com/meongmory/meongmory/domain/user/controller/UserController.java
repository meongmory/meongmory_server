package com.meongmory.meongmory.domain.user.controller;

import com.meongmory.meongmory.domain.user.dto.request.SignInUserReq;
import com.meongmory.meongmory.domain.user.dto.request.SignUpUserReq;
import com.meongmory.meongmory.domain.user.dto.response.SignUpUserRes;
import com.meongmory.meongmory.domain.user.service.UserService;
import com.meongmory.meongmory.global.response.ResponseCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @Operation(summary = "회원가입", description = "회원가입을 진행한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)회원가입 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "400", description = "(U0002)이미 존재하는 전화번호", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @PostMapping("/signUp")
  public ResponseCustom<SignUpUserRes> signUp(@RequestBody @Valid SignUpUserReq signUpUserReq
  ) {
    return ResponseCustom.OK(userService.signUp(signUpUserReq));
  }

  @Operation(summary = "로그인", description = "로그인을 진행한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)로그인 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "404", description = "(U0004)해당 유저를 찾을 수 없다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @PostMapping("/signIn")
  public ResponseCustom<SignUpUserRes> signIn(@RequestBody @Valid SignInUserReq signInUserReq
  ) {
    return ResponseCustom.OK(userService.signIn(signInUserReq));
  }

}
