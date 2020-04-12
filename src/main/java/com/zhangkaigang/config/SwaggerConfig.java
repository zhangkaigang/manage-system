package com.zhangkaigang.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description:TODO
 * Swagger相关注解
 * @Api：用在类上，表示标识这个类是swagger的资源 其中tags：表示说明 ；value：也是说明，可以使用tags替代
 * @ApiOperation：用于方法； 表示一个http请求的操作
 * （1）value：请求接口说明
 * （2）httpMethod：请求方式
 * （3）response：返回值类型
 * @ApiParam：用于方法，参数，字段说明；
 * （1）name：参数名
 * （2）dataType：参数类型
 * （3）required：参数是否必须传true | false
 * （4）value：说明参数的意思
 * （5）defaultValue：参数的默认值
 * @ApiResponses：用于表示一组响应
 * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
 * （1）code：数字，例如400
 * （2）message：信息例如请求参数没填好
 * （3）response：抛出异常的类

 * @Author:zhang.kaigang
 * @Date:2020/4/11
 * @Version:1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后台管理系统接口服务列表")
//                .description("XXX项目接口测试")
                .version("1.0.0")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .build();
    }


    @Bean
    public RequestMappingInfoHandlerMapping requestMapping(){
        return new RequestMappingHandlerMapping();
    }

}