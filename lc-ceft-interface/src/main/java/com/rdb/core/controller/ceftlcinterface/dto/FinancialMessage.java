// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : FinancialMessage
// ************************************************************************************************
//
// Copyright(C) 2019 SprintLabs (Pvt) Ltd.
// All rights reserved.
//
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
// SprintLabs (Pvt) Ltd.
//
// This copy of the Source Code is intended for SprintLabs (Pvt) Ltd 's internal use only
// and is
// intended for view by persons duly authorized by the management of SprintLabs (Pvt) Ltd. 
//No part of this file may be reproduced or distributed in any form or by any
// means without the written approval of the Management of SprintLabs (Pvt) Ltd.
//
// *************************************************************************************************

package com.rdb.core.controller.ceftlcinterface.dto;

import com.rdb.core.controller.ceftlcinterface.enums.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class FinancialMessage extends BaseMessage {

    /** The property primaryAccountNumber  is the primary account number received in iso field 2.*/
    private String primaryAccountNumber;

    /** The property fromAcctType is the from account type received in processing code in iso field 3 */
    private AccountType fromAcctType;

    /** The property toAcctType is the to account type received in processing code in iso field 3 */
    private AccountType toAcctType;

    /** The property transactionType is the transaction type received in processing code in iso field 3 */
    private TransactionType transactionType;

    /** The property amount is the to transaction amount received in iso field 4 */
    private Double amount;

    /** The property transactionTime is the local transaction time received in iso field 12 { format n 6 (hhmmss)} */
    private LocalTime transactionTime;

    /** The property transactionTime is the local transaction date received in iso field 13 { format n 4 (MMDD)} */
    private LocalDate transactionDate;

    /** The property settlementDate is the settlement date received in iso field 15 { format n 4 (MMDD)} */
  ////  private LocalDate settlementDate;

    /** The property captureDate is the settlement date received in iso field 17 { format n 4 (MMDD)} */
    private LocalDate captureDate;

    /** The property merchantType is the channel code of the acquirer received in iso field 18 { format n 4} */
    private MerchantType merchantType;

    /** The property posEntryMode is the pos entry mode iso field 22 { format n 3} */
  //  private String posEntryMode;

    /** The property posConditionCode is the pos condition code iso field 25 { format n 2} */
    //private String posConditionCode;

    /** The property acquirerIdentificationCode is the acquirer identification iso field 32 { format an 12} */
    //private String acquirerIdentificationCode;

    /** The property retRefNo is the retrieval reference number iso field 37 { format an 12} */
    private String retRefNo;

    /** The property authorizationNumber is the authorization number iso field 38 { format an 6} */
    private String authorizationNumber;

    /** The property currencyCode is the currency code  in iso field 49 { format n 3} */
    private String currencyCode;

    /** The property additionalTerminalData is the additional terminal data  in iso field 60 { format an 6} */
    //private String additionalTerminalData;

    /** The property receiverIdentificationCode is the receiver identification in iso field 100 */
    private String receiverIdentificationCode;

    /** The property beneficiaryCardNo is the beneficiary card no  in iso field 120 { tag 001} */
    private String beneficiaryCardNo;

    /** The property destAccountNo is the Destination Account Number  in iso field 120 { tag 002} */
    private String destAccountNo;

    /** The property cardholder primary account number is the card holder PAN  in iso field 120 { tag 003} */
    private String cardholderPAN;

    /** The property cardholderAccount is card holder number in iso field 120 { tag 004} */
    private String cardholderAccount;

    /**
     * Field 120 Tag 005 Destination Bank Code
     */
    private String destBankCode;

    /**
     * Field 120 Tag 006 Originating Bank Code
     */
    private String orgBankCode;

    /**
     * Field 120 Tag 007 Destination Branch Code
     */
    private String destBranchCode;

    /**
     * Field 120 Tag 008 Originating Branch Code
     */
    private String orgBranchCode;

    /**
     * Field 120 Tag 009 Destination Account Holders Name
     */
    private String destAccountHolderName;

    /**
     * Field 120 Tag 010 Card\Originating Account Holders Name
     */
    private String orgAccountHolderName;

    /**
     * Field 120 Tag 011
     */
    private String particulars;

    /**
     * Field 120 Tag 012
     */
    private String reference;

    /**
     * Field 120 Tag 013 Transaction Code Transaction Code
     */
    private TransactionCode transactionCode;

    @Override
    public Boolean isRequest() {

        return getMessageTypeIdentification() == MessageTypeIdentification.FINANCIAL_REQ;
    }
}
