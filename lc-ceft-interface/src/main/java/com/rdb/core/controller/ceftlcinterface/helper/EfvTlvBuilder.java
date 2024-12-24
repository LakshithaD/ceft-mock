// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : EfvTlvBuilder
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

package com.rdb.core.controller.ceftlcinterface.helper;

import com.rdb.core.controller.ceftlcinterface.dto.FinancialMessage;
import com.rdb.core.controller.ceftlcinterface.enums.EftTlvTag;
import com.rdb.core.controller.ceftlcinterface.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public class EfvTlvBuilder {

    private static Map<EftTlvTag, String> tagPropertyMap = new LinkedHashMap<>();
    static {

        tagPropertyMap.put(EftTlvTag.BENEFICIARY_CARD_NO, "beneficiaryCardNo");
        tagPropertyMap.put(EftTlvTag.DESTINATION_ACCOUNT_NO, "destAccountNo");
        tagPropertyMap.put(EftTlvTag.CARDHOLDER_PAN, "cardholderPAN");
        tagPropertyMap.put(EftTlvTag.CARDHOLDER_ACCOUNT, "cardholderAccount");
        tagPropertyMap.put(EftTlvTag.DESTINATION_BANK_CODE, "destBankCode");
        tagPropertyMap.put(EftTlvTag.ORIGINATING_BANK_CODE, "orgBankCode");
        tagPropertyMap.put(EftTlvTag.DESTINATION_BRANCH_CODE, "destBranchCode");
        tagPropertyMap.put(EftTlvTag.ORIGINATING_BRANCH_CODE, "orgBranchCode");
        tagPropertyMap.put(EftTlvTag.DESTINATION_ACCOUNT_HOLDERS_NAME, "destAccountHolderName");
        tagPropertyMap.put(EftTlvTag.ACCOUNT_HOLDERS_NAME, "orgAccountHolderName");
        tagPropertyMap.put(EftTlvTag.PARTICULARS, "particulars");
        tagPropertyMap.put(EftTlvTag.REFERENCE, "reference");
        tagPropertyMap.put(EftTlvTag.TRANSACTION_CODE, "transactionCode.code");
       // tagPropertyMap.put(EftTlvTag.TRANSACTION_ID, "beneficiaryCardNo");
       // tagPropertyMap.put(EftTlvTag.ORIGINATOR_WALLET_NUMBER, "beneficiaryCardNo");
       // tagPropertyMap.put(EftTlvTag.DESTINATION_WALLET_NUMBER, "beneficiaryCardNo");
       // tagPropertyMap.put(EftTlvTag.ADDITIONAL_DATA, "beneficiaryCardNo");
    }

    public static String buildEfvTlv(FinancialMessage financialMessage) {

        String eftTlvData = tagPropertyMap
                                    .entrySet()
                                    .stream()
                                    .map(entry ->  buildTagLength(entry.getKey(), entry.getValue(), financialMessage))
                                    .filter(StringUtil::isNotEmpty)
                                    .reduce("", StringUtils::join);
        return eftTlvData;
    }

    private static String buildTagLength(EftTlvTag tag, String propertyName, FinancialMessage financialMessage) {

        String tagValue = getTagValue(propertyName, financialMessage);
        if (StringUtil.isNotEmpty(tagValue)) {
            StringBuilder builder = new StringBuilder();
            builder.append(tag.getTagId());
            builder.append(String.format("%03d", tagValue.length()));
            builder.append(tagValue);
            return builder.toString();
        }
        return null;
    }

    private static String getTagValue(String propertyName, FinancialMessage financialMessage) {

        try {
            String tagValue = (String)PropertyUtils.getProperty(financialMessage, propertyName);
            return tagValue;
        } catch(IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return StringUtil.EMPTY;
    }
}
