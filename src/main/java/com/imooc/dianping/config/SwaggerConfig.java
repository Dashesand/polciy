package com.imooc.dianping.config;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-10 11:52
 **/


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        // 创建一个 swagger 的 bean 实例
        return new Docket(DocumentationType.SWAGGER_2)

                // 配置接口信息
                .select() // 设置扫描接口
                // 配置如何扫描接口
                .apis(RequestHandlerSelectors
                                .any() // 扫描全部的接口，默认
                                //.none() // 全部不扫描
                                //.basePackage("com.imooc.dianping.controller,com.imooc.dianping.model") // 扫描指定包下的接口，最为常用
                        //.withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                        //.withMethodAnnotation(PostMapping.class) // 扫描带有只当注解的方法接口

                )
                .paths(PathSelectors
                                .any() // 满足条件的路径，该断言总为true
                        //.none() // 不满足条件的路径，该断言总为false（可用于生成环境屏蔽 swagger）
                        //.ant("/user/**") // 满足字符串表达式路径
                        //.regex("") // 符合正则的路径
                )
                .build();
    }


}

