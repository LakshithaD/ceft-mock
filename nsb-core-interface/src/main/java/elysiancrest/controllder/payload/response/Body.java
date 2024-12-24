package elysiancrest.controllder.payload.response;

import elysiancrest.controllder.payload.BaseNsbRestRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Body extends BaseNsbRestRequest {
    private String debitAmount;
    private String mdrAmount;
    private String reference;
    private String originatingBranchCode;
    private String destinationAccountHolderName;
    private String destinationBankCode;
    private String originatingBankCode;
    private String channel;
    private String stan;
    private String originatingAccountHolderName;
    private String destinationBranchCode;
    private String originatingAccountType;
    private String merchantType;
    private String destinationAccount;
    private String responseCode;
    private String mdr;
}
