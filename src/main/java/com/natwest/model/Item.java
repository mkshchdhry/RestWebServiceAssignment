package com.natwest.model;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@ToString
public class Item {
    @JsonAlias({"id"})
    private String id;

    @JsonAlias({"name"})
    private String name;

    @JsonAlias({"data"})
    private Data data;

    @JsonAlias({"createdAt"})
    private String createdAt;

    @JsonAlias({"updatedAt"})
    private String updatedAt;

}
