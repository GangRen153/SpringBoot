package com.mashibing.springboot.utils.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.ResourceUtils;

public class MainGenerator {
    public MainGenerator() {
    }

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList();
        boolean overwrite = true;
        File configFile = ResourceUtils.getFile("classpath:generator-config.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration configuration = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator generator = new MyBatisGenerator(configuration, callback, warnings);
        generator.generate((ProgressCallback)null);
    }
}
