package com.rdb.core.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class Request {
    private String sourceAccountNo;
    private String destinationAccountNo;
    private String amount;
    private String narration;
}
