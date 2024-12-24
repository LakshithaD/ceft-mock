// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : TransactionCode
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

package com.rdb.core.controller.ceftlcinterface.enums;

public enum TransactionCode {

    BALANCE_INQUIRY("00"),
    CALL_MONEY_TRANSACTIONS("11"),
    FOREIGN_EXCHANGE_SETTLEMENTS("12"),
    CALL_MONEY_SETTLEMENT("16"),
    STANDING_ORDERS("21"),
    INSURANCE("22"),
    SALARIES("23"),
    PENSIONS("24"),
    EPF_REFUNDS("25"),
    ETF("26"),
    PURCHASE_FOREGIN_CURRENCY_NOTES("27"),
    COVER_PAYMENTS("28"),
    FOREIGN_DRAFTS("29"),
    ELECTRICITY_BILLS("31"),
    TELEPHONE_BILLS("32"),
    WATER_BILLS("33"),
    INSURANCE_DEBIT("34"),
    EPF_CONTRIBUTION_RECEIPTS("35"),
    LC_CHARGES("36"),
    CREDIT_CARDS("41"),
    JP_REGISTRATION("42"),
    JP_TRANSACTION("43"),
    CUSTOMER_TRANSFER_DR("44"),
    OUTWARDS_BILLS_PROCEEDS("51"),
    CUSTOMER_TRANSFER("52"),
    INWARDS_FOREIGN_REMITANCE("53"),
    CREDIT_CARD_SETTLEMENT("54"),
    DEVIDEND_PAYMENTS("55"),
    STOCK_TRANSACTIONS("56"),
    IPO_REFUNDS("56"),
    SL_CUSTOMS_PAYMENTS("58"),
    MERCHANT_CR("70"),
    CUSTOMER_DR("45"),
    LPOPP("62");


    String code;

    private TransactionCode(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    public static TransactionCode forCode(String code) {

        for (TransactionCode transactionCode : TransactionCode.values()) {

            if (transactionCode.getCode().equalsIgnoreCase(code)) {

                return transactionCode;
            }
        }
        return null;
    }
}
