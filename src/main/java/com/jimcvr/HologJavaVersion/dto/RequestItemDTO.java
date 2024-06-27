package com.jimcvr.HologJavaVersion.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class RequestItemDTO {
    @NotNull
    private String name;
    private String description;
    private String picture;
    private Double score;
    private Date date;
    private String status;
    private Boolean custom;
    @NotNull
    private long categoryId;

    public RequestItemDTO(String name, String description, String picture, Double score, Date date, String status, Boolean custom, long categoryId) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.score = score;
        this.date = date;
        this.status = status;
        this.custom = custom;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCustom() {
        return custom;
    }

    public void setCustom(Boolean custom) {
        this.custom = custom;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}