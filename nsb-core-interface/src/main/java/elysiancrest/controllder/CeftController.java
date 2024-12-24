package elysiancrest.controllder;

import elysiancrest.controllder.payload.response.NsbRestResponse;
import elysiancrest.helper.MessageBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CeftController {

    @PostMapping(value = "/nsb/postInwardCeftRequestNsb")
    public ResponseEntity<NsbRestResponse> getTranDetailsByConsumerId(@RequestBody @Valid CeftInwardRequest request) {

        log.info("ceft-inward {}", request);
       // bankIntegration.performCeftInward(request);
        log.info("ceft-inward ");
        //return "{\"header\":{\"transactionStatus\":\"Live\",\"audit\":{\"T24_time\":1433,\"responseParse_time\":0,\"requestParse_time\":836,\"versionNumber\":\"1\"},\"id\":\"CEFT000000000001241522L0PR\",\"status\":\"Success\"},\"body\":{\"reference\":\"test\",\"originatingBranchCode\":\"999\",\"destinationAccountHolderName\":\"test\",\"destinationBankCode\":\"6719\",\"originatingBankCode\":\"6728\",\"channel\":\"IB\",\"stan\":\"012240\",\"originatingAccountHolderName\":\"Sasi Test\",\"destinationBranchCode\":\"130\",\"originatingAccountType\":\"01\",\"merchantType\":\"IB_EFT\",\"destinationAccount\":\"100000601448\"}}";

        String body = "{\n" +
                "      \"header\":{\n" +
                "         \"audit\":{\n" +
                "            \"T24_time\":429,\n" +
                "            \"responseParse_time\":1,\n" +
                "            \"requestParse_time\":3\n" +
                "         },\n" +
                "         \"status\":\"failed\"\n" +
                "      },\n" +
                "      \"error\":{\n" +
                "         \"type\":\"BUSINESS\",\n" +
                "         \"errorDetails\":[\n" +
                "            {\n" +
                "               \"code\":\"TGVCP-002\",\n" +
                "               \"message\":\"RUNTIME EXCEPTION T24COREEXCEPTION CUSTOMER NUMBER DOES NOT EXIST\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   }";
        //return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        ResponseEntity<NsbRestResponse> response = new ResponseEntity<>(MessageBuilder.buildInvalidResponse(), HttpStatus.BAD_REQUEST);
        return response;
     }
}
