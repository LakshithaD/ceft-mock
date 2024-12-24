package com.rdb.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ReversalController {

    @PostMapping(path = "/api/fund-transfer/reversal/account-to-gl")
    @ResponseBody
    public Response accountToGl(@RequestBody ReversalRequest request) {

        log.info("request received");
        return new Response("0000", "Success", 28);
    }
}
