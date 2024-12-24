package com.rdb.core.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RdbCoreBankRequest {

    private String sourceAccountNo;
    private String destinationAccountNo;
    private Double amount;
    private String narration;
}
