package com.mashibing.springboot.entity;

import java.util.Date;
import java.util.List;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user")
public class User {

    @Id
    private String id;
    @Field
    private String brand;
    @Field
    private String model;
    @Field
    private Integer ram;
    @Field
    private Double price;
    @Field
    private Date manufactureDate;
    @Field
    private String content;
    @Field
    private List<String> tags;
    @Field(type = FieldType.Keyword)
    private String introduce;

    public User() {
    }

    public String getId() {
        return this.id;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    public Integer getRam() {
        return this.ram;
    }

    public Double getPrice() {
        return this.price;
    }

    public Date getManufactureDate() {
        return this.manufactureDate;
    }

    public String getContent() {
        return this.content;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public void setRam(final Integer ram) {
        this.ram = ram;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setManufactureDate(final Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void setTags(final List<String> tags) {
        this.tags = tags;
    }

    public void setIntroduce(final String introduce) {
        this.introduce = introduce;
    }

}
