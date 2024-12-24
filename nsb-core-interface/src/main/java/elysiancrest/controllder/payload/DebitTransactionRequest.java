package elysiancrest.controllder.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebitTransactionRequest extends BaseNsbRestRequest {
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
