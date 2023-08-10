package com.meongmory.meongmory.domain.user.controller;

import com.meongmory.meongmory.domain.user.dto.request.ModifyMyPageReq;
import com.meongmory.meongmory.domain.user.dto.request.SignInUserReq;
import com.meongmory.meongmory.domain.user.dto.request.SignUpUserReq;
import com.meongmory.meongmory.domain.user.dto.response.MyPageRes;
import com.meongmory.meongmory.domain.user.dto.response.SignUpUserRes;
import com.meongmory.meongmory.domain.user.service.UserService;
import com.meongmory.meongmory.global.resolver.Auth;
import com.meongmory.meongmory.global.resolver.IsLogin;
import com.meongmory.meongmory.global.resolver.LoginStatus;
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

  @Operation(summary = "마이페이지 조회", description = "마이페이지 조회를 진행한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)마이페이지 조회 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @Auth
  @GetMapping("/myPage")
  public ResponseCustom<MyPageRes> getMyPage(@IsLogin LoginStatus loginStatus
  ) {
    return ResponseCustom.OK(userService.getMyPage(loginStatus.getUserId()));
  }

  @Operation(summary = "마이페이지 수정", description = "마이페이지 수정을 진행한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)마이페이지 수정 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "404", description = "(U0003)닉네임을 입력해주세요.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @Auth
  @PatchMapping("/myPage")
  public ResponseCustom<Void> modifyMyPage(@RequestBody @Valid ModifyMyPageReq modifyMyPageReq,
          @IsLogin LoginStatus loginStatus
  ) {
    userService.modifyMyPage(loginStatus.getUserId(),modifyMyPageReq);
    return ResponseCustom.OK();
  }

  @Operation(summary = "회원탈퇴", description = "회원탈퇴를 진행한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)회원탈퇴 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @Auth
  @DeleteMapping("/delete")
  public ResponseCustom<Void> deleteUser(@IsLogin LoginStatus loginStatus
  ) {
    userService.deleteUser(loginStatus.getUserId());
    return ResponseCustom.OK();
  }

  @Operation(summary = "로그아웃", description = "로그아웃을 진행한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)로그아웃 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @Auth
  @PostMapping("/logout")
  public ResponseCustom<Void> logout(@IsLogin LoginStatus loginStatus
  ) {
    userService.logout(loginStatus.getUserId());
    return ResponseCustom.OK();
  }






}
