package com.boc.mock.core.controller;

import com.boc.mock.core.controller.dto.response.Ifx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@RestController
@Slf4j
public class CoreBankController {

    @PostMapping("/boc-api/boc/post")
    public String ft(@RequestBody String string) throws JAXBException {

        log.info(string);
        return buildIfx();
    }

    private String buildIfx() throws JAXBException {

        Ifx ifx = new Ifx();

        Ifx.GeneralStatus generalStatus = new Ifx.GeneralStatus();
        generalStatus.setStatusCode("0");
        generalStatus.setStatusDesc("SUCCESS");
        ifx.setGeneralStatus(generalStatus);

        Ifx.Body body = new Ifx.Body();
        body.setType("SV");

        Ifx.Details details = new Ifx.Details();
        details.setBody(body);
        ifx.setDetails(details);

        JAXBContext jaxbContext = JAXBContext.newInstance(Ifx.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        // Write the XML to a StringWriter
        StringWriter sw = new StringWriter();
        marshaller.marshal(ifx, sw);

        // Get the XML as a string
        String swString = sw.toString();

        return swString;
    }
}
