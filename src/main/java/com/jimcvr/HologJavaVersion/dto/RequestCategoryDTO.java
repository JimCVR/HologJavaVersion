package com.jimcvr.HologJavaVersion.dto;

import jakarta.validation.constraints.NotNull;

public class RequestCategoryDTO {
    @NotNull
    private String name;

    private long iconId;

    public RequestCategoryDTO(String name, long iconId) {

        this.name = name;
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public long getIconId() {
        return iconId;
    }

    public void setIconId(long iconId) {
        this.iconId = iconId;
    }
}
