package com.mashibing.springboot.utils.generator;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class MyJavaClientGenerator extends AbstractJavaClientGenerator {
    public MyJavaClientGenerator() {
        super(true);
    }

    public MyJavaClientGenerator(boolean requiresXMLGenerator) {
        super(requiresXMLGenerator);
    }

    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new MyXMLMapperGenerator();
    }

    public List<CompilationUnit> getCompilationUnits() {
        JavaMapperGenerator generator = new JavaMapperGenerator();
        generator.setContext(this.context);
        generator.setIntrospectedTable(this.introspectedTable);
        generator.setProgressCallback(this.progressCallback);
        generator.setWarnings(this.warnings);
        List<CompilationUnit> compilationUnits = generator.getCompilationUnits();
        if (!compilationUnits.isEmpty()) {
            this.addBatchInsertMethed((Interface)compilationUnits.get(0));
        }

        return compilationUnits;
    }

    private void addBatchInsertMethed(Interface anInterface) {
        boolean bool = this.introspectedTable.getRules().generateCountByExample();
        bool = true;
        if (bool) {
            AbstractJavaMapperMethodGenerator methodGenerator = new MyBatchInsertMethodGenerator();
            methodGenerator.setContext(this.context);
            methodGenerator.setWarnings(this.warnings);
            methodGenerator.setProgressCallback(this.progressCallback);
            methodGenerator.setIntrospectedTable(this.introspectedTable);
            methodGenerator.addInterfaceElements(anInterface);
        }

    }

    static class MyBatchInsertMethodGenerator extends AbstractJavaMapperMethodGenerator {
        MyBatchInsertMethodGenerator() {
        }

        public void addInterfaceElements(Interface interfaze) {
            String baseRecordType = this.introspectedTable.getBaseRecordType();
            FullyQualifiedJavaType fgjt = new FullyQualifiedJavaType("java.util.List<" + baseRecordType + ">");
            Set<FullyQualifiedJavaType> importedTypes = new TreeSet();
            importedTypes.add(fgjt);
            Method method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setReturnType(new FullyQualifiedJavaType("int"));
            method.setName("batchInsert");
            method.addParameter(new Parameter(fgjt, "list"));
            this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);
            this.context.getPlugins().clientCountByExampleMethodGenerated(method, interfaze, this.introspectedTable);
            boolean bool = true;
            if (bool) {
                interfaze.addImportedTypes(importedTypes);
                interfaze.addMethod(method);
            }

        }
    }

    static class MyXMLMapperGenerator extends XMLMapperGenerator {
        public MyXMLMapperGenerator() {
        }

        protected XmlElement getSqlMapElement() {
            XmlElement answer = super.getSqlMapElement();
            this.addBatchInsertElement(answer);
            return answer;
        }

        private void addBatchInsertElement(XmlElement parentElement) {
            String namespace = this.introspectedTable.getMyBatis3SqlMapNamespace();
            parentElement.addAttribute(new Attribute("namespace", namespace));
            this.context.getCommentGenerator().addComment(parentElement);
            parentElement.addElement(new TextElement("<!-- 自定义生成 batchInsert 语句 -->"));
            if (this.introspectedTable.getRules().generateInsert()) {
                AbstractXmlElementGenerator generator = new BatchInsertElementGenerator();
                this.initializeAndExecuteGenerator(generator, parentElement);
            }

        }
    }
}
