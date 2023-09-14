package com.natwest.model;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@ToString
public class Data {
    @JsonAlias({"year"})
    private int year;

    @JsonAlias({"year"})
    private double price;

    @JsonAlias({"CPU model"})
    private String cpuModel;

    @JsonAlias({"Hard disk size"})
    private String hardDiskSize;

}
