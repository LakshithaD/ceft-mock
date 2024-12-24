// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : MerchantType
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

public enum MerchantType {

    ATM_EFT("6011"),
    IB_EFT("6013"),
    MB_EFT("6014"),
    POS_EFT("6015"),
    KIOSK_EFT("6016"),
    OTHER_EFT("6017"),
    JP_REGISTRATION("4101"),
    JP_TRANSACTION("4102"),
    QR_TYPE_1_MERCHANT_CR("5101"), //customer internal merchant outside
    QR_TYPE_2_CUSTOMER_DR("5201"),//merchant internal customer outside
    QR_TYPE_3_CUSTOMER_DR("5301"),//merchant external customer external but same bank
    QR_TYPE_3_MERCHANT_CR("5302"),//merchant external customer external but same bank
    QR_TYPE_4_CUSTOMER_DR("5401"),//merchant external customer external but different bank
    QR_TYPE_4_MERCHANT_CR("5402"),//merchant external customer external but different bank
    UNION_PAY("6001"), //union pay 16th March 2023

    NPCL_INDIA("6005"), //OIC -32/2023

    //LPOPP
    IRD("4106");

    String code;

    private MerchantType(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    public static MerchantType forCode(String code) {

        for (MerchantType merchantType : MerchantType.values()) {

            if (merchantType.getCode().equalsIgnoreCase(code)) {

                return merchantType;
            }
        }
        return null;
    }
}
