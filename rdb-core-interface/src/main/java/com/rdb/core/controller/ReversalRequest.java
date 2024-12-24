package com.rdb.core.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
class ReversalRequest {
    private Date tranDate;
    private String destinationGlAccountNo;
    private String fromBatchNo;
    private String narration;
}


