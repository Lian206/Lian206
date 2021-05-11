package com.lym.small.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @desc: Swagger2 配置类
 * @author: Lian
 * @time: 2021/5/10 9:52
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket userRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("用户模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lym.small.controller.user"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket pictureRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("图片模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lym.small.controller.picture"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket messageRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("留言模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lym.small.controller.message"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        //作者的联系方式
        Contact contact = new Contact("Lianyiming","https://www.baidu.com","2236370700@qq.com");
        return new ApiInfoBuilder()
                .title("连一铭的Swagger2")
                .description("有一种想见不敢见的伤痛 有一种爱还埋藏在我心中")
                .contact("Lian")
                .version("1.0")
                .build();
    }

}
