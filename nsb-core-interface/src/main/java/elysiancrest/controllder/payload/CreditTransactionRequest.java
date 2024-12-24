package elysiancrest.controllder.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditTransactionRequest extends BaseNsbRestRequest {

    private String transactionType;
    private String debitCurrency;
    private String originatingAccount;
    private String originatingAccountType;
    private String originatingAccountHolderName;
    private String originatingBankCode;
    private String originatingBranchCode;
    private String debitAmount;
    private String creditCurrency;
    private String creditAccount;
    private String destinationAccount;
    private String destinationAccountType;
    private String destinationAccountHolderName;
    private String destinationBankCode;
    private String destinationBranchCode;
    private String particulars;
    private String merchantType;
    private String channel;
    private String retRefNo;
    private String stan;
    private String reference;
    private String merchantId;
    private String mdr;
    private String mdrAmount;
    private String responseCode;
    private String errors;
}
