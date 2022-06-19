package com.mashibing.springboot.utils.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.config.GeneratedKey;

public class BatchInsertElementGenerator extends AbstractXmlElementGenerator {
    private static final String BATCH_INSERT_STATEMENT_ID = "batchInsert";
    private static final int NEW_LINE_CHARACTER_LIMIT = 80;

    public BatchInsertElementGenerator() {
    }

    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("insert");
        answer.addAttribute(new Attribute("id", "batchInsert"));
        answer.addAttribute(new Attribute("parameterType", FullyQualifiedJavaType.getNewListInstance().getFullyQualifiedName()));
        this.context.getCommentGenerator().addComment(answer);
        GeneratedKey gk = this.introspectedTable.getGeneratedKey();
        if (gk != null) {
            IntrospectedColumn introspectedColumn = this.introspectedTable.getColumn(gk.getColumn());
            if (introspectedColumn != null) {
                if (gk.isJdbcStandard()) {
                    answer.addAttribute(new Attribute("useGenerateKeys", "true"));
                    answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
                } else {
                    answer.addElement(this.getSelectKey(introspectedColumn, gk));
                }
            }
        }

        StringBuilder insertClause = new StringBuilder();
        StringBuilder valuesClause = new StringBuilder();
        insertClause.append("insert into ");
        insertClause.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime());
        insertClause.append(" (");
        List<String> valuesClauses = new ArrayList();
        valuesClauses.add("values ");
        valuesClauses.add("<foreach collection=\"list\" separator=\",\" index=\"index\" item=\"item\">");
        valuesClauses.add(" (");
        Iterator<IntrospectedColumn> iter = this.introspectedTable.getAllColumns().iterator();

        while(iter.hasNext()) {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iter.next();
            if (!introspectedColumn.isIdentity()) {
                insertClause.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
                if (introspectedColumn.getDefaultValue() == null) {
                    this.getValueClauseWhenNoDefaultValue(valuesClause, valuesClauses, iter, introspectedColumn, 1);
                } else {
                    this.getChooseString(valuesClause, valuesClauses, introspectedColumn, iter);
                }

                this.appendCommaWhenHasNext(insertClause, iter);
                if (insertClause.length() > 80) {
                    answer.addElement(new TextElement(insertClause.toString()));
                    insertClause.setLength(0);
                    OutputUtilities.xmlIndent(insertClause, 1);
                }
            }
        }

        insertClause.append(")");
        answer.addElement(new TextElement(insertClause.toString()));
        valuesClause.append(")");
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        valuesClause.append("</foreach>");
        valuesClauses.add(valuesClause.toString());
        Iterator var11 = valuesClauses.iterator();

        while(var11.hasNext()) {
            String clause = (String)var11.next();
            answer.addElement(new TextElement(clause));
        }

        if (this.context.getPlugins().sqlMapInsertElementGenerated(answer, this.introspectedTable)) {
            parentElement.addElement(answer);
        }

    }

    private void getChooseString(StringBuilder valuesClause, List<String> valuesClauses, IntrospectedColumn introspectedColumn, Iterator<IntrospectedColumn> iter) {
        OutputUtilities.xmlIndent(valuesClause, 1);
        valuesClause.append("<choose>");
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        OutputUtilities.xmlIndent(valuesClause, 2);
        valuesClause.append("<when test=\"item.");
        valuesClause.append(introspectedColumn.getJavaProperty((String)null));
        valuesClause.append(" != null\" >");
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        this.getValueClauseWhenNoDefaultValue(valuesClause, valuesClauses, iter, introspectedColumn, 3);
        OutputUtilities.xmlIndent(valuesClause, 2);
        valuesClause.append("</when>");
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        OutputUtilities.xmlIndent(valuesClause, 2);
        valuesClause.append("<otherwise>");
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        OutputUtilities.xmlIndent(valuesClause, 3);
        valuesClause.append(this.wrapQuotaPair(introspectedColumn.getDefaultValue()));
        this.appendCommaWhenHasNext(valuesClause, iter);
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        OutputUtilities.xmlIndent(valuesClause, 2);
        valuesClause.append("</otherwise>");
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        OutputUtilities.xmlIndent(valuesClause, 1);
        valuesClause.append("</choose>");
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
    }

    private void getValueClauseWhenNoDefaultValue(StringBuilder valuesClause, List<String> valuesClauses, Iterator<IntrospectedColumn> iter, IntrospectedColumn introspectedColumn, int indentLevel) {
        OutputUtilities.xmlIndent(valuesClause, indentLevel);
        valuesClause.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
        this.insertItemAfterLeftBrace(valuesClause);
        this.appendCommaWhenHasNext(valuesClause, iter);
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
    }

    private void appendCommaWhenHasNext(StringBuilder insertClause, Iterator<IntrospectedColumn> iter) {
        if (iter.hasNext()) {
            insertClause.append(", ");
        }

    }

    private void insertItemAfterLeftBrace(StringBuilder valuesClause) {
        int index = valuesClause.indexOf("{");
        if (index != -1) {
            valuesClause.insert(index + 1, "item.");
        }

    }

    private String wrapQuotaPair(String defaultValue) {
        return "'" + defaultValue + "'";
    }
}
