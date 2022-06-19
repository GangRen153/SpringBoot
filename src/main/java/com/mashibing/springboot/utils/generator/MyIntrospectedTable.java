package com.mashibing.springboot.utils.generator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.IntrospectedTable.InternalAttribute;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

public class MyIntrospectedTable extends IntrospectedTableMyBatis3Impl {
    boolean customAttributes = true;

    public MyIntrospectedTable() {
    }

    protected void initializeAbstractGenerator(AbstractGenerator abstractGenerator, List<String> warnings, ProgressCallback progressCallback) {
        super.initializeAbstractGenerator(abstractGenerator, warnings, progressCallback);
        if (this.customAttributes) {
            this.customAttributes = false;
            Iterator var4 = this.internalAttributes.entrySet().iterator();

            while(var4.hasNext()) {
                Map.Entry<IntrospectedTable.InternalAttribute, String> map = (Map.Entry)var4.next();
                IntrospectedTable.InternalAttribute key = (IntrospectedTable.InternalAttribute)map.getKey();
                String value = (String)map.getValue();
                if (key == InternalAttribute.ATTR_BASE_RECORD_TYPE) {
                    this.internalAttributes.put(key, value + "Entity");
                }

                if (key == InternalAttribute.ATTR_EXAMPLE_TYPE) {
                    this.internalAttributes.put(key, this.customReplace(value, "Example", "Param"));
                } else if (key == InternalAttribute.ATTR_MYBATIS3_XML_MAPPER_FILE_NAME) {
                    this.internalAttributes.put(key, "Base" + this.customReplace(value, "Mapper", "DAO"));
                } else if (key == InternalAttribute.ATTR_MYBATIS3_JAVA_MAPPER_TYPE) {
                    String str = this.customReplace(value, "Mapper", "DAO");
                    String[] split = str.split("\\.");
                    String newName = "Base" + split[split.length - 1];
                    this.internalAttributes.put(key, this.customReplace(str, split[split.length - 1], newName));
                } else if (key == InternalAttribute.ATTR_DAO_IMPLEMENTATION_TYPE) {
                    this.internalAttributes.put(key, this.customReplace(value, "Mapper", "DAO"));
                } else {
                    this.internalAttributes.put(key, this.customReplace(value, "Example", "Param"));
                }
            }
        }

    }

    private String customReplace(String value, String regex, String target) {
        String rest = value;
        if (regex != null && target != null) {
            String pattern = "(?i)" + regex;
            rest = value.replaceAll(pattern, target);
        }

        return rest;
    }
}

