package com.damda.ideaconcert.damda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.damda.ideaconcert.damda"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, resMsgList());
    }
    private List<ResponseMessage> resMsgList() {
        List<ResponseMessage> list = new ArrayList<ResponseMessage>();
        list.add(new ResponseMessageBuilder()
                .code(500)
                .message("Internal Server Error")
                .responseModel(new ModelRef("Error"))
                .build());
        list.add(new ResponseMessageBuilder()
                .code(400)
                .message("Bad Request")
                .build());
        list.add(new ResponseMessageBuilder()
                .code(404)
                .message("Not Found")
                .build());
        return list;
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("damda API DOC")
                .description("damda 2024의 API 문서입니다.")
                .build();
    }
}
