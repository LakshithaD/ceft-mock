package elysiancrest.helper;

import elysiancrest.controllder.payload.response.Audit;
import elysiancrest.controllder.payload.response.Body;
import elysiancrest.controllder.payload.response.Header;
import elysiancrest.controllder.payload.response.NsbRestResponse;

public abstract class MessageBuilder {

    public static NsbRestResponse buildInvalidResponse() {

        NsbRestResponse response = new NsbRestResponse();

        Header header = new Header();
        header.setTransactionStatus("Live");
        header.setId("CEFT00000000000124152FB4Y1");
        header.setStatus("success");
        header.setUniqueIdentifier("uniqueIdentifier");
        response.setHeader(header);

        Audit audit = new Audit();
        audit.setT24_time(775d);
        audit.setResponseParse_time(1d);
        audit.setRequestParse_time(3d);
        audit.setVersionNumber(1d);
        header.setAudit(audit);

        Body body = new Body();
        body.setOriginatingBranchCode("036");
        body.setChannel("IB");
        body.setOriginatingAccountHolderName("R H Samaliarachchi");
        body.setDestinationBranchCode("000");
        body.setOriginatingAccountType("01");
        body.setDebitAmount("1800.0");
        body.setMdrAmount("9.00");
        body.setResponseCode("14");
        body.setReference("ABDC@1234");
        body.setDestinationBankCode("6719");
        body.setOriginatingBankCode("6990");
        body.setStan("264330");
        body.setMdr("0.5%");
        body.setMerchantType("IB_EFT");
        body.setResponseCode("14");
        body.setDestinationAccount("1671900015022404494388700000");
        response.setBody(body);

        return response;
    }
}
