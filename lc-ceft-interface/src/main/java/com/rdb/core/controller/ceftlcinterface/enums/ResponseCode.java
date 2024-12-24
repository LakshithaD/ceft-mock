// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : ResponseCode
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

public enum ResponseCode {

    SUCCESS("00"),
    REFER_TO_CARD_ISSUER("01"),
    REFER_TO_CARD_ISSUER_SP("02"),
    INVALID_MERCHANT("03"),
    PICK_UP("04"),
   // DO_NOT_HONOUR("05"),
    ERROR("06"),
    PICK_UP_CARD_SP("07"),
    ISSUER_TIMEOUT("08"),
    NO_ORG_TRAN("09"),
    HARDWARE_ERROR("96"),
    INVALID_ACCT("14"),//todo check
    INVALID_TRAN("12"),
    INVALID_AMOUNT("13"),
    INVALID_CARD_NO("14"),
    INSUFFICIENT_BALANCE("51"),//todo chek
    CUSTOMER_CANCELLATION("17"),
    NO_CREDIT_ACCOUNT("39"),
    RESPONSE_LATE("68"),
    FAIL("91"),
    VALIDATION_ERROR("98"),
    ACQUIRER_TIMEOUT("E1");
//Timeout 96
    //
    private String code;

    ResponseCode(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    public static ResponseCode forCode(String code) {

        for (ResponseCode responseCode : ResponseCode.values()) {

            if (responseCode.getCode().equalsIgnoreCase(code)) {

                return responseCode;
            }
        }
        return null;
    }
}
