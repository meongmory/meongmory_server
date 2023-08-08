package com.meongmory.meongmory.global.resolver;


import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import com.meongmory.meongmory.global.util.token.TokenUtil;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;


@RequiredArgsConstructor
@Component
public class LoginResolver implements HandlerMethodArgumentResolver{

    private final TokenUtil tokenUtils;
    private final Environment env;

    @Override
    public boolean supportsParameter(MethodParameter parameter)
    {
        return parameter.hasParameterAnnotation(IsLogin.class)
                                   &&
               LoginStatus.class.equals(parameter.getParameterType());
    }

    @Nullable
    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  @NotNull NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception
    {
        Auth auth = parameter.getMethodAnnotation(Auth.class);

        if (auth == null)
            throw new BaseException(BaseResponseCode.TOKEN_ANNOTATION_NULL);

        String accessToken = webRequest.getHeader(Objects.requireNonNull(env.getProperty("jwt.auth-header")));
        if(accessToken == null || !tokenUtils.isValidToken(tokenUtils.parseJustTokenFromFullToken(accessToken)))
            return LoginStatus.getNotLoginStatus();

        Long userId = Long.valueOf(tokenUtils.getUserIdFromFullToken(accessToken));

        if (!auth.optional() && userId == null) {
            return LoginStatus.getNotLoginStatus();
        }

        return LoginStatus.builder().isLogin(true).userId(userId).build();
    }
}
