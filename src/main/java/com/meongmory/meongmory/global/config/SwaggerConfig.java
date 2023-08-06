package com.meongmory.meongmory.global.config;


import com.fasterxml.classmate.TypeResolver;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseRes;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import com.meongmory.meongmory.global.response.ResponseCustom;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String API_NAME = "MEONGMORY_SERVER_API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "MEONGMORY_SERVER_API_명세서";


    @Bean
    public Docket api(TypeResolver typeResolver){
        return new Docket(DocumentationType.SWAGGER_2)
                .additionalModels(
                        typeResolver.resolve(BaseRes.class)
                )
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_NAME)
                .version(API_VERSION)
                .description(API_DESCRIPTION)
                .build();
    }




}