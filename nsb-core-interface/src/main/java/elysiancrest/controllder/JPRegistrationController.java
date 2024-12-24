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
public class JPRegistrationController {

    String invalidResponse = "{\n" +
            "    \"header\": {\n" +
            "        \"transactionStatus\": \"Live\",\n" +
            "        \"audit\": {\n" +
            "            \"T24_time\": 775,\n" +
            "            \"responseParse_time\": 1,\n" +
            "            \"requestParse_time\": 3,\n" +
            "            \"versionNumber\": \"1\"\n" +
            "        },\n" +
            "        \"id\": \"CEFT00000000000124152FB4Y1\",\n" +
            "        \"status\": \"success\",\n" +
            "        \"uniqueIdentifier\": \"CFTINW240784414857905.02\"\n" +
            "    },\n" +
            "    \"body\": {\n" +
            "        \"originatingBranchCode\": \"036\",\n" +
            "        \"channel\": \"IB\",\n" +
            "        \"originatingAccountHolderName\": \"R H Samaliarachchi\",\n" +
            "        \"destinationBranchCode\": \"000\",\n" +
            "        \"originatingAccountType\": \"01\",\n" +
            "        \"debitAmount\": \"1800.0\",\n" +
            "        \"mdrAmount\": \"9.00\",\n" +
            "        \"responseCode\": \"14\",\n" +
            "        \"reference\": \"ABDC@1234\",\n" +
            "        \"destinationBankCode\": \"6719\",\n" +
            "        \"originatingBankCode\": \"6990\",\n" +
            "        \"stan\": \"264330\",\n" +
            "        \"mdr\": \"0.5%\",\n" +
            "        \"merchantType\": \"IB_EFT\",\n" +
            "        \"destinationAccount\": \"1671900015022404494388700000\"\n" +
            "    }\n" +
            "}";

    String validResponse = "{\n" +
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
    @PostMapping(value = "/nsb/postJustPayNsb")
    public ResponseEntity<NsbRestResponse> getTranDetailsByConsumerId(@RequestBody @Valid JpRegistrationRequest request) {

        log.info("jp-registration {}", request);
        log.info("jp-registration ");
        ResponseEntity<NsbRestResponse> response = new ResponseEntity<>(MessageBuilder.buildInvalidResponse(), HttpStatus.OK);
        return response;
    }
}
