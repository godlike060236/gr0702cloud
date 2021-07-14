package com.gr.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.gr.pojo.BasePojo;

import java.util.HashMap;
import java.util.Map;

public class MybatisGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // gc.setOutputDir(projectPath + "/src/main/java");
        // 定义pojo路径
        String pojoPath = projectPath + "/pojo";
        // 定义其他项目路径
        String otherPath = projectPath + "/cart";
        gc.setAuthor("guorui");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.119.22:3308/shop?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.gr");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        Map<String, String> pathInfo = new HashMap<>();
        //pathInfo.put("xml_path", otherPath + "/src/main/resources/com/gr/mapper");
        pathInfo.put("entity_path", pojoPath + "/src/main/java/com/gr/pojo");
        //pathInfo.put("mapper_path", otherPath + "/src/main/java/com/gr/mapper");
        //pathInfo.put("service_path", otherPath + "/src/main/java/com/gr/service");
        //pathInfo.put("service_impl_path", otherPath + "/src/main/java/com/gr/service/impl");
        //pathInfo.put("controller_path", otherPath + "/src/main/java/com/gr/controller");
        pc.setPathInfo(pathInfo);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(BasePojo.class);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude("cart_stock");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
