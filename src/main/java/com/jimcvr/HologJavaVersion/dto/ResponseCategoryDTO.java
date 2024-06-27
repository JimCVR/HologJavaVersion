package com.jimcvr.HologJavaVersion.dto;

import jakarta.validation.constraints.NotNull;

public class ResponseCategoryDTO {
    @NotNull
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String userId;
    private long iconId;

    public ResponseCategoryDTO(long id, String name, String userId, long iconId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.iconId = iconId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    @Override
    public String toString() {
        return "ResponseCategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", iconId=" + iconId +
                '}';
    }
}
