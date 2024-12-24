// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : TransactionType
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum TransactionType {

    CREDIT_TRANSACTION("48"), DEBIT_TRANSACTION("49");

    String code;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionType.class);

    private TransactionType(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    public static TransactionType forCode(String code) {

        for (TransactionType transactionType : TransactionType.values()) {

            if (transactionType.getCode().equalsIgnoreCase(code)) {

                return transactionType;
            }
        }
        LOGGER.warn("Undefined TransactionType {}", code);
        return null;
    }
}
