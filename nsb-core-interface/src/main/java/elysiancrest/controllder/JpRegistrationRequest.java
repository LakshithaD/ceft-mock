package elysiancrest.controllder;

import lombok.Data;

@Data
public class JpRegistrationRequest extends BaseNsbRestRequest {
    private String accountNumber;
    private String accountName;
    private String nic;
    private String destinationBankCode;
    private String amount;
    private String channel;
    private String reference;
    private String particulars;
    private String returnReferenceNumber;
    private String stan;
    private String transactionType;
}
