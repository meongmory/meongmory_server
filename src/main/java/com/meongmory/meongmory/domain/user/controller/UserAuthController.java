package com.meongmory.meongmory.domain.user.controller;
import com.meongmory.meongmory.domain.user.dto.request.UserAuthTokenRequest;

import com.meongmory.meongmory.global.response.ResponseCustom;
import com.meongmory.meongmory.global.util.token.TokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class UserAuthController {

  private final TokenUtil tokenUtils;
  @Operation(summary = "토큰 재발급", description = "토큰 재발급을 진행한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)토큰 재발급 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "400", description = "(T0002)토큰이 만료되었다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "404", description = "(U0001)해당 유저를 찾을 수 없다. \n (SM0001)해당 번호를 찾을 수 없다", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @PostMapping("/renew")
  public ResponseCustom<String> accessToken(@RequestBody @Valid UserAuthTokenRequest userAuthTokenRequest) {
    return ResponseCustom.OK(tokenUtils.accessExpiration(userAuthTokenRequest));
  }


}
