package com.rdb.core.controller.ceftlcinterface.util;

import com.rdb.core.controller.ceftlcinterface.dto.FinancialMessage;
import com.rdb.core.controller.ceftlcinterface.dto.ReversalMessage;
import com.rdb.core.controller.ceftlcinterface.enums.*;

import java.time.LocalDateTime;

public class MessageBuilder {

    public static FinancialMessage build() {

        FinancialMessage financialMessage = new FinancialMessage();
        financialMessage.setMessageTypeIdentification(MessageTypeIdentification.FINANCIAL_REQ);
        finCommon(financialMessage);
        return financialMessage;
    }

    private static void finCommon(FinancialMessage financialMessage) {

        //---From account data
        //financialMessage.setCardholderPAN(request.getOriginatingAccountType() == AccountType.CREDIT_CARD ? request.getOriginatingAccount() : null);
        financialMessage.setCardholderAccount("010000012001");
        financialMessage.setFromAcctType(AccountType.SAVING);
        financialMessage.setOrgAccountHolderName("Test Name");
        financialMessage.setOrgBankCode("6010");
        financialMessage.setOrgBranchCode("001");
        //---To account data
        //financialMessage.setBeneficiaryCardNo(request.getDestinationAccountType() == AccountType.CREDIT_CARD ? request.getDestinationAccount() : null);
       // financialMessage.setDestAccountNo("1648100000000000000000010021");
        financialMessage.setDestAccountNo("123456789012");
        financialMessage.setToAcctType(AccountType.SAVING);
        financialMessage.setDestAccountHolderName("Jonn Alen");
        financialMessage.setDestBankCode("7020");
        financialMessage.setDestBranchCode("002");

        LocalDateTime now = LocalDateTime.now();
        financialMessage.setTransmissionDateAndTime(now);
        financialMessage.setTransactionDate(now.toLocalDate());
        financialMessage.setTransactionTime(now.toLocalTime());

        financialMessage.setAmount(Double.parseDouble("1234567890.12"));
        financialMessage.setCurrencyCode("144");

        financialMessage.setReference("Test reference");
        financialMessage.setParticulars("Teat Particulars");

        //JP registration
        financialMessage.setReference("1234567890");
        financialMessage.setTransactionCode(TransactionCode.JP_REGISTRATION_FROM_MB);
        financialMessage.setMerchantType(MerchantType.JP_REGISTRATION);
        financialMessage.setTransactionType(TransactionType.DEBIT_TRANSACTION);
        //JP transaction
//        financialMessage.setTransactionCode(TransactionCode.JP_TRANSACTION);
//        financialMessage.setMerchantType(MerchantType.JP_TRANSACTION);
//        financialMessage.setTransactionType(TransactionType.DEBIT_TRANSACTION);

        //credit card payment
//        financialMessage.setTransactionCode(TransactionCode.CREDIT_CARD_SETTLEMENT);
//        financialMessage.setMerchantType(MerchantType.IB_EFT);
//        financialMessage.setBeneficiaryCardNo("4544282000020973");
//        financialMessage.setTransactionType(TransactionType.CREDIT_TRANSACTION);

        //cusotmer ft
//        financialMessage.setTransactionCode(TransactionCode.CUSTOMER_TRANSFER);
//        financialMessage.setMerchantType(MerchantType.IB_EFT);
//        //financialMessage.setCardholderPAN("1234567890123456");
//        financialMessage.setTransactionType(TransactionType.CREDIT_TRANSACTION);

        //QR Merchant credit
//        financialMessage.setTransactionCode(TransactionCode.MERCHANT_CR);
//        financialMessage.setMerchantType(MerchantType.MB_EFT);
//        financialMessage.setCardholderPAN("1234567890123456");
//        financialMessage.setTransactionType(TransactionType.CREDIT_TRANSACTION);

        // debit transaction
//        financialMessage.setTransactionCode(TransactionCode.CUSTOMER_DR);
//        financialMessage.setMerchantType(MerchantType.MB_EFT);
//        financialMessage.setCardholderPAN("1234567890123456");
//        financialMessage.setTransactionType(TransactionType.DEBIT_TRANSACTION);

        financialMessage.setStan("123456");
        financialMessage.setRetRefNo("JP123456789");
    }

    public static ReversalMessage reversal() {

        ReversalMessage reversalMessage = new ReversalMessage();
        reversalMessage.setMessageTypeIdentification(MessageTypeIdentification.REVERSAL_REQ);

        finCommon(reversalMessage);
        reversalMessage.setOrgStan("123456");
        reversalMessage.setOrgRetRefNo("JP123456789");
        reversalMessage.setOriginalTypeIdentification(MessageTypeIdentification.FINANCIAL_REQ);
        reversalMessage.setOrgTransmissionDateAndTime(LocalDateTime.now());
        reversalMessage.setOrgAcquirerIdentification("JP123456789");
        reversalMessage.setResponseCode(ResponseCode.CUSTOMER_CANCELLATION);
        return reversalMessage;
    }
}
