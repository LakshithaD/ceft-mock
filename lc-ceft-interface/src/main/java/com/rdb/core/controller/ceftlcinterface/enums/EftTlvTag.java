// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : EftTlvTag
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

public enum EftTlvTag {

    BENEFICIARY_CARD_NO("001"),
    DESTINATION_ACCOUNT_NO("002"),
    CARDHOLDER_PAN("003"),
    CARDHOLDER_ACCOUNT("004"),
    DESTINATION_BANK_CODE("005"),
    ORIGINATING_BANK_CODE("006"),
    DESTINATION_BRANCH_CODE("007"),
    ORIGINATING_BRANCH_CODE("008"),
    DESTINATION_ACCOUNT_HOLDERS_NAME("009"),
    ACCOUNT_HOLDERS_NAME("010"),
    PARTICULARS("011"),
    REFERENCE("012"),
    TRANSACTION_CODE("013"),
    TRANSACTION_ID("014"),
    ORIGINATOR_WALLET_NUMBER("015"),
    DESTINATION_WALLET_NUMBER("016"),
    ADDITIONAL_DATA("017");

    String tagId;

    private EftTlvTag(String tagId) {
        this.tagId = tagId;
    }

    /**
     * @return the code
     */
    public String getTagId() {
        return tagId;
    }

    public static EftTlvTag forCode(String code) {

        for (EftTlvTag tag : EftTlvTag.values()) {

            if (tag.getTagId().equalsIgnoreCase(code)) {

                return tag;
            }
        }
        return null;
    }

}
