// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : IsoHelper
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
import com.rdb.core.controller.ceftlcinterface.dto.NetworkMessage;
import com.rdb.core.controller.ceftlcinterface.dto.ReversalMessage;
import com.rdb.core.controller.ceftlcinterface.enums.*;
import com.rdb.core.controller.ceftlcinterface.util.DateUtil;
import com.rdb.core.controller.ceftlcinterface.util.StringUtil;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.Calendar;

@Component
public class IsoHelper {

    @Autowired
    MessageFactory<IsoMessage> factory;

    private static Integer headerLength = 4;

    private static final Logger LOGGER = LoggerFactory.getLogger(IsoHelper.class);

    public byte[] encodeNetworkMessage(NetworkMessage networkMessage) {

        String stan = DateUtil.format(LocalDateTime.now(), DateUtil.HHmmss);
        IsoMessage msg = factory.newMessage(MessageTypeIdentification.NETWORK_MGT_REQ.getVal());
        msg.setIsoHeader(factory.getIsoHeader(MessageTypeIdentification.NETWORK_MGT_REQ.getVal()));
        msg.setValue(7, Calendar.getInstance().getTime(), IsoType.DATE10, 10);
        msg.setValue(11, StringUtil.isNotNullNorEmpty(networkMessage.getStan()) ? networkMessage.getStan() : stan, IsoType.NUMERIC, 6);
        msg.setValue(70, networkMessage.getNetworkMgtCode().getCode(), IsoType.NUMERIC, 3);
        msg.setBinaryBitmap(factory.isUseBinaryBitmap());
        LOGGER.info("Sending iso message {}", IsoLogMessageBuilder.format(msg));
        return getBytes(msg);
    }

    public byte[] encodeNetworkResponseMessage(NetworkMessage networkMessage) {

        IsoMessage requestMessage = networkMessage.getRequestIsoMessage();
        IsoMessage responseMsg = factory.createResponse(requestMessage);
        responseMsg.setIsoHeader(requestMessage.getIsoHeader());
        responseMsg.setValue(39, ResponseCode.SUCCESS.getCode(), IsoType.NUMERIC, 2);
        LOGGER.info("Sending iso message {}", IsoLogMessageBuilder.format(responseMsg));
        return getBytes(responseMsg);
    }

    public byte[] encodeReversalMessage(ReversalMessage reversalMessage) {

        IsoMessage isoMsg = factory.newMessage(reversalMessage.getMessageTypeIdentification().getVal());
        isoMsg.setIsoHeader(factory.getIsoHeader(reversalMessage.getMessageTypeIdentification().getVal()));
        isoMsg.setBinaryBitmap(factory.isUseBinaryBitmap());
        fillIsoFinMsg(reversalMessage, isoMsg);
        fillIsoRevMsg(reversalMessage, isoMsg);
        LOGGER.info("Sending iso message {}", IsoLogMessageBuilder.format(isoMsg));
        ByteBuffer byteBuffer = isoMsg.writeToBuffer(headerLength);
        return byteBuffer.array();
    }

    public byte[] encodeFinancialMessage(FinancialMessage financialMessage) {

        IsoMessage isoMsg = factory.newMessage(MessageTypeIdentification.FINANCIAL_REQ.getVal());
        isoMsg.setIsoHeader(factory.getIsoHeader(MessageTypeIdentification.FINANCIAL_REQ.getVal()));
        isoMsg.setBinaryBitmap(factory.isUseBinaryBitmap());
        fillIsoFinMsg(financialMessage, isoMsg);
        fillIsoFinMsg60(financialMessage, isoMsg);
        LOGGER.info("Sending iso message {}", IsoLogMessageBuilder.format(isoMsg));
        ByteBuffer byteBuffer = isoMsg.writeToBuffer(headerLength);
        return byteBuffer.array();
    }

    private static void fillIsoFinMsg(FinancialMessage finMsg, IsoMessage isoMsg) {

        // isoMsg.setValue(2, finMsg.getPrimaryAccountNumber(), IsoType.LLVAR, 19);
        isoMsg.setValue(3, buildProcessCode(finMsg.getTransactionType(), finMsg.getFromAcctType(), finMsg.getToAcctType()), IsoType.NUMERIC, 6);
        isoMsg.setValue(4, finMsg.getAmount(), IsoType.AMOUNT, 12);
        isoMsg.setValue(7, Calendar.getInstance().getTime(), IsoType.DATE10, 10);
        isoMsg.setValue(11, finMsg.getStan(), IsoType.ALPHA, 6);
        isoMsg.setValue(12, DateUtil.convertToDateViaInstant(finMsg.getTransactionTime()), IsoType.TIME, 6);
        isoMsg.setValue(13, DateUtil.convertToDateViaInstant(finMsg.getTransactionDate()), IsoType.DATE4, 4);
        // isoMsg.setValue(15, finMsg.getSettlementDate(), IsoType.DATE4, 4);
        isoMsg.setValue(17, DateUtil.convertToDateViaInstant(finMsg.getTransactionDate()), IsoType.DATE4, 4);
        isoMsg.setValue(18, finMsg.getMerchantType().getCode(), IsoType.NUMERIC, 4);
        isoMsg.setValue(22, "012", IsoType.NUMERIC, 3);
        isoMsg.setValue(25, /*finMsg.getPosConditionCode()*/ "00", IsoType.NUMERIC, 2);
        isoMsg.setValue(32, finMsg.getOrgBankCode(), IsoType.LLVAR, 11);
        isoMsg.setValue(37, finMsg.getRetRefNo(), IsoType.ALPHA, 12);
        // isoMsg.setValue(41, finMsg.getAcceptorTerminalIdentification(), IsoType.ALPHA, 8);
        // isoMsg.setValue(42, finMsg.getAcceptorIdentificatioCode(), IsoType.ALPHA, 15);
        // isoMsg.setValue(43, finMsg.getAcceptorNameLocation(), IsoType.ALPHA, 40);
        isoMsg.setValue(49, finMsg.getCurrencyCode(), IsoType.NUMERIC, 3);
        //isoMsg.setValue(60, "100090", IsoType.ALPHA, 6);
        // isoMsg.setValue(100, finMsg.getReceiverIdentificationCode(), IsoType.LLVAR, 40);
        // isoMsg.setValue(102, finMsg.getAccId(), IsoType.LLVAR, 28);
        String eftTlvData = EfvTlvBuilder.buildEfvTlv(finMsg);
        isoMsg.setValue(120, eftTlvData, IsoType.LLLVAR, 12);
    }

    private static void fillIsoRevMsg(ReversalMessage revMsg, IsoMessage isoMsg) {

        // isoMsg.setValue(17, DateUtil.format(revMsg.getCaptureDate(), DateUtil.CAPTURE_DATE_FORMAT), IsoType.NUMERIC, 4);
        isoMsg.setValue(39, revMsg.getResponseCode().getCode(), IsoType.ALPHA, 2);
        isoMsg.setValue(90, buildOrgData(revMsg), IsoType.ALPHA, 43);

    }

    private static void fillIsoFinMsg60(FinancialMessage finMsg, IsoMessage isoMsg) {
        isoMsg.setValue(60, "100090", IsoType.ALPHA, 6);
    }

    private byte[] getNetworkMgtMessageBytes(NetworkMgtCode networkMgtCode) {

        IsoMessage msg = factory.newMessage(MessageTypeIdentification.NETWORK_MGT_REQ.getVal());
        msg.setIsoHeader(factory.getIsoHeader(MessageTypeIdentification.NETWORK_MGT_REQ.getVal()));
        msg.setValue(7, Calendar.getInstance().getTime(), IsoType.DATE10, 10);
        msg.setValue(11, "123456", IsoType.NUMERIC, 6);
        msg.setValue(70, networkMgtCode.getCode(), IsoType.NUMERIC, 3);
        msg.setBinaryBitmap(factory.isUseBinaryBitmap());
        return getBytes(msg);

    }

    private byte[] getBytes(IsoMessage isoMessage) {

        ByteBuffer byteBuffer = isoMessage.writeToBuffer(headerLength);
        return byteBuffer.array();
    }

    public static String buildOrgData(ReversalMessage revMsg) {

        if (StringUtil.isNotNullNorEmpty(revMsg.getOrgStan(), revMsg.getOrgAcquirerIdentification(), revMsg.getOrgRetRefNo())
                && revMsg.getOrgAcquirerIdentification() != null && revMsg.getOrgTransmissionDateAndTime() != null) {

            StringBuilder builder = new StringBuilder();
            builder.append(revMsg.getOriginalTypeIdentification().getMti());
            builder.append(StringUtil.leftPad(revMsg.getOrgStan(), 6, StringUtil.ZERO));
            builder.append(DateUtil.format(revMsg.getOrgTransmissionDateAndTime(), DateUtil.TRANSAMISSION_DATE_TIME_FORMAT));
            builder.append(StringUtil.leftPad(revMsg.getOrgAcquirerIdentification(), 11, StringUtil.ZERO));
            builder.append(StringUtil.leftPad(revMsg.getOrgRetRefNo(), 12, StringUtil.SPACE));
            LOGGER.info("Built original data element : {}", builder.toString());
            return builder.toString();
        } else {

            LOGGER.error("Either of the original date element is null or empty");
            return null;
        }
    }

    public static String buildProcessCode(TransactionType transactionType, AccountType fromActType, AccountType toActType) {

        StringBuilder builder = new StringBuilder();
        builder.append(transactionType.getCode());
        builder.append(fromActType != null ? fromActType.getCode() : AccountType.UNSPECIFIED.getCode());
        builder.append(toActType != null ? toActType.getCode() : AccountType.UNSPECIFIED.getCode());
        return builder.toString();
    }

}
