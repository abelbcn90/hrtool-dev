package com.wedonegood.hrtool.config;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Sets;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .useDefaultResponseMessages(false)
                .globalOperationParameters(
                        Arrays.asList(new ParameterBuilder()
                                .name("X-Auth-User")
                                .description("Username")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .build(),
                        new ParameterBuilder()
                                .name("X-Auth-Client")
                                .description("Client ID")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .build()))
                .directModelSubstitute(ResponseEntity.class, Void.class)
                .groupName("/api/v1")
                .apiInfo(new ApiInfoBuilder()
                        .title("HR Tool API")
                        .description("HR Tool - Config module REST API")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wedonegood"))
                .paths(PathSelectors.regex("/api/v1/.*"))
                .build();
    }

}