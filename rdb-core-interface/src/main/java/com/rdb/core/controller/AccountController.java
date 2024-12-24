package com.rdb.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AccountController {

    @PostMapping(path = "/api/fund-transfer/account-to-account")
    @ResponseBody
    public Response accountToAccount(@RequestBody Request request) {

        log.info("request received");
        return new Response("0000", "Success", 28);
    }

    @PostMapping(path = "/api/fund-transfer/account-to-gl")
    @ResponseBody
    public Response accountToGl(@RequestBody Request request) {

        log.info("request received");
        return new Response("0000", "Success", 28);
    }

    @PostMapping(path = "/api/fund-transfer/gl-to-gl")
    @ResponseBody
    public Response glToGl(@RequestBody Request request) {

        log.info("request received");
        return new Response("0000", "Success", 28);
    }


}

