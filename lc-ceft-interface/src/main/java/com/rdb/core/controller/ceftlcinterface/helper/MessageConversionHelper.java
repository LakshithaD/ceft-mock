// *************************************************************************************************
//
// PROJECT : ceft-interface
// PRODUCT : CEFT-SUITE
// ************************************************************************************************
//
// Copyright(C) 2013 Fortunaglobal (PRIVATE) LIMITED
// All rights reserved.
//
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
// Fortunaglobal(PRIVATE) LIMITED.
//
// This copy of the Source Code is intended for Fortunaglobal (PRIVATE) LIMITED 's internal use only
// and is
// intended for view by persons duly authorized by the management of Fortunaglobal (PRIVATE)
// LIMITED. No
// part of this file may be reproduced or distributed in any form or by any
// means without the written approval of the Management of Fortunaglobal (PRIVATE) LIMITED.
//
// *************************************************************************************************
// REVISIONS
// Author : Matarage
// Date : Aug 27, 2014 6:19:56 PM
// Since : version 1.0
// Description
// **************************************************************************************************

package com.rdb.core.controller.ceftlcinterface.helper;

import com.rdb.core.controller.ceftlcinterface.dto.BaseMessage;
import com.rdb.core.controller.ceftlcinterface.dto.FinancialMessage;
import com.rdb.core.controller.ceftlcinterface.dto.NetworkMessage;
import com.rdb.core.controller.ceftlcinterface.dto.ReversalMessage;
import com.rdb.core.controller.ceftlcinterface.enums.*;
import com.rdb.core.controller.ceftlcinterface.util.DateUtil;
import com.rdb.core.controller.ceftlcinterface.util.StringUtil;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.IsoValue;
import com.solab.iso8583.MessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Component
public class MessageConversionHelper {

	@Autowired
    MessageFactory<IsoMessage> factory;

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageConversionHelper.class);

	public BaseMessage convertIsoMessage(byte[] messageBytes) throws UnsupportedEncodingException, ParseException {

		IsoMessage isoMessage = factory.parseMessage(messageBytes, 20);
		LOGGER.info("Received iso message {}", IsoLogMessageBuilder.format(isoMessage));
		MessageTypeIdentification mti = MessageTypeIdentification.forVal( isoMessage.getType());
		switch (mti) {

			case NETWORK_MGT_REQ:
			case NETWORK_MGT_RESPONSE:

				return createNetworkMsg(isoMessage);

			case FINANCIAL_REQ:
			case FINANCIAL_RESPONSE:

				return createFinMsg(isoMessage);

			case REVERSAL_REQ:
			case REVERSAL_RESPONSE:
			case REVERSAL_ADVICE_REQ:

				return createReversalMsg(isoMessage);
			default:
				LOGGER.warn("Unsupported MTI {}", isoMessage.getType());
				break;
		}
		return null;

	}

	public byte[] convertMessageToBytes(BaseMessage message) {

		IsoMessage requestMessage = message.getRequestIsoMessage();
		IsoMessage responseMsg = factory.createResponse(requestMessage);
		responseMsg.setIsoHeader(requestMessage.getIsoHeader());
		fillCommonFields(responseMsg);
		if (message instanceof FinancialMessage) {

			FinancialMessage finMsg = (FinancialMessage) message;
			responseMsg.setValue( 38, finMsg.getAuthorizationNumber(), IsoType.ALPHA, 6);
			responseMsg.setValue(100, finMsg.getReceiverIdentificationCode(), IsoType.LLVAR, 11);

		}
		if (message.getResponseCode() != null) {
			responseMsg.setValue(39, message.getResponseCode().getCode(), IsoType.NUMERIC, 2);
		}
		LOGGER.info("Response iso message {}", IsoLogMessageBuilder.format(responseMsg));
		String headerLength = "4";// PropertyFactory.getPorperty(String.format(PropertyFactory.PropertyKey.ISSUER_HEADER_LENGTH,
		// issuer));
		// IsoMessageUtil.printIsoMessage(message, LOGGER);
		//String toLog = format(message);
		//LOGGER.info("Sending iso Message : " + toLog);
		ByteBuffer byteBuffer = responseMsg.writeToBuffer(Integer.parseInt( headerLength));
		return byteBuffer.array();
	}



	private void fillCommonFields(IsoMessage responseMsg) {

		//responseMsg.setValue( 39, baseMessage.getResponseCode(), IsoType.NUMERIC, 2);
		responseMsg.setField(60, null);
		// responseMsg.setField(102, null);
		responseMsg.setField(103, null);
	}

	private NetworkMessage createNetworkMsg(IsoMessage isoMessage) {

		NetworkMessage networkMessage = new NetworkMessage();
		fillCommonData(networkMessage, isoMessage);
		String networkMgtCode = getValue(isoMessage, 70);
		networkMessage.setNetworkMgtCode(NetworkMgtCode.forCode(networkMgtCode));
		return networkMessage;
	}

	private FinancialMessage createFinMsg(IsoMessage isoMessage) {

		FinancialMessage financialMessage = new FinancialMessage();
		fillCommonData(financialMessage, isoMessage);
		processProcessingCode(financialMessage, getValue(isoMessage, 3));
		finCommon(financialMessage, isoMessage);
		breakEftTlvData(financialMessage, getValue(isoMessage, 120));
		setOriginatingBankFromFiledIfNotInEftTlv(financialMessage, isoMessage);
		return financialMessage;
	}

	private FinancialMessage createReversalMsg(IsoMessage isoMessage) {

		ReversalMessage financialMessage = new ReversalMessage();
		fillCommonData(financialMessage, isoMessage);
		processProcessingCode(financialMessage, getValue(isoMessage, 3));
		finCommon(financialMessage, isoMessage);
		breakEftTlvData(financialMessage, getValue(isoMessage, 120));
		processOriginalDataElement(financialMessage, getValue(isoMessage, 90));
		setOriginatingBankFromFiledIfNotInEftTlv(financialMessage, isoMessage);
		return financialMessage;
	}

	private void processProcessingCode(FinancialMessage financialMessage, String processingCode) {

		if (StringUtils.hasText( processingCode) && processingCode.length() == 6) {

			financialMessage.setTransactionType(TransactionType.forCode(processingCode.substring(0, 2)));
			financialMessage.setFromAcctType(AccountType.forCode(processingCode.substring(2, 4)));
			financialMessage.setToAcctType(AccountType.forCode(processingCode.substring(4, 6)));
		}
	}

	private void setOriginatingBankFromFiledIfNotInEftTlv(FinancialMessage financialMessage, IsoMessage isoMessage) {
		if (StringUtil.isEmpty(financialMessage.getOrgBankCode())) {
			financialMessage.setOrgBankCode(getValue(isoMessage, 32));
		}
	}

	private void finCommon(FinancialMessage finMsg, IsoMessage isoMessage) {

		finMsg.setPrimaryAccountNumber(getValue(isoMessage, 2));
		finMsg.setAmount(convertAmt(getValue(isoMessage, 4)));
	//	finMsg.setAmount(convertAmt(MsgConversionHelper.getValue(msg, 4)));
		finMsg.setTransactionTime(getLocalTimeValue(isoMessage, 12));
		finMsg.setTransactionDate(getLocalDateValue(isoMessage, 13));
	//	finMsg.setSettlementDate(MsgConversionHelper.getDateValue(msg, 15));
		finMsg.setCaptureDate(getLocalDateValue(isoMessage, 17));
		finMsg.setMerchantType(MerchantType.forCode(getValue(isoMessage, 18)));
		//finMsg.setPosEntryMode(getValue(isoMessage, 22));
		//finMsg.setPosConditionCode(getValue(isoMessage, 25));
		//finMsg.setAcquirerIdentificationCode(getValue(isoMessage, 32));
		// finMsg.setTr(getValue(msg, 35));
		finMsg.setRetRefNo(getValue(isoMessage, 37));
		finMsg.setAuthorizationNumber(getValue(isoMessage, 38));
		String respondCodeVal = getValue(isoMessage, 39);
		finMsg.setResponseCode(respondCodeVal != null ? ResponseCode.forCode(respondCodeVal) : ResponseCode.FAIL);
	//	finMsg.setAcceptorTerminalIdentification(MsgConversionHelper.getValue(msg, 41));
	//	finMsg.setAcceptorIdentificatioCode(MsgConversionHelper.getValue(msg, 42));
	//	finMsg.setAcceptorNameLocation(MsgConversionHelper.getValue(msg, 43));
		finMsg.setCurrencyCode(getValue(isoMessage, 49));
		// finMsg.setpi(getValue(msg, 52));
		// finMsg.setCurrencyCode(getValue(msg, 55));
		//finMsg.setTerminalDetails(MsgConversionHelper.getValue(msg, 60));
		// finMsg.setReceiverIdentificationCode(getValue(msg, 60));
		//finMsg.setAccId(MsgConversionHelper.getValue(msg, 102));
		//finMsg.setEftTlvData(MsgConversionHelper.getValue(msg, 120));

		//finMsg.setMsgAuthCode(MsgConversionHelper.getValue(msg, 128));

	}

	private static void breakEftTlvData(FinancialMessage finMsg, String eftTlvData) {

		if (eftTlvData == null || eftTlvData.isEmpty()) {

			return;
		}
		EftTlvTag tag = EftTlvTag.forCode(eftTlvData.substring(0, 3));
		String length = eftTlvData.substring(3, 6);
		Integer lengthInt = Integer.parseInt(length);
		String val = eftTlvData.substring(6, lengthInt + 6);

		switch (tag) {

			case BENEFICIARY_CARD_NO:

				finMsg.setBeneficiaryCardNo(val);
				break;

			case DESTINATION_ACCOUNT_NO:

				finMsg.setDestAccountNo(val);
				break;

			case CARDHOLDER_PAN:

				finMsg.setCardholderPAN(val);
				break;

			case CARDHOLDER_ACCOUNT:

				finMsg.setCardholderAccount(val);
				break;

			case DESTINATION_BANK_CODE:
				finMsg.setReceiverIdentificationCode(val);
				finMsg.setDestBankCode(val);
				break;

			case ORIGINATING_BANK_CODE:
				finMsg.setOrgBankCode(val);
				break;

			case DESTINATION_BRANCH_CODE:

				finMsg.setDestBranchCode(val);
				break;

			case ORIGINATING_BRANCH_CODE:

				finMsg.setOrgBranchCode(val);
				break;

			case DESTINATION_ACCOUNT_HOLDERS_NAME:

				finMsg.setDestAccountHolderName(val);
				break;

			case ACCOUNT_HOLDERS_NAME:

				finMsg.setOrgAccountHolderName(val);
				break;

			case PARTICULARS:

				finMsg.setParticulars(val);
				break;

			case REFERENCE:

				finMsg.setReference(val);
				break;

			case TRANSACTION_CODE:

				finMsg.setTransactionCode(TransactionCode.forCode(val));
				break;
		}
		breakEftTlvData(finMsg, eftTlvData.substring(lengthInt + 6));
	}



	private void fillCommonData(BaseMessage baseMessage, IsoMessage isoMessage) {

		baseMessage.setRequestIsoMessage(isoMessage);
		baseMessage.setTransmissionDateAndTime(getLocalDateTimeValue(isoMessage, 7));
		baseMessage.setStan(getValue(isoMessage, 11));
		baseMessage.setMessageTypeIdentification(MessageTypeIdentification.forVal(isoMessage.getType()) );
	}

	private static String getValue(IsoMessage message, int index) {

		IsoValue<?> val = message.getField( index);
		if (val != null) {

			return val.toString();
		} else {

			return null;
		}
	}

	private static Date getDateValue(IsoMessage message, int index) {

		IsoValue<Date> val = message.getField(index);
		if (val != null) {

			return val.getValue();
		} else {

			return null;
		}

	}

	private static LocalDateTime getLocalDateTimeValue(IsoMessage isoMessage, int index) {

		Date date = getDateValue(isoMessage, index);
		if (date != null) {

			LocalDateTime localDateTime = DateUtil.convertToLocalDateTime(date);
			return localDateTime;
		}
		return null;
	}

	private static LocalDate getLocalDateValue(IsoMessage isoMessage, int index) {

		Date date = getDateValue(isoMessage, index);
		if (date != null) {

			LocalDate localDateTime = DateUtil.convertToLocalDate(date);
			return localDateTime;
		}
		return null;
	}

	private static LocalTime getLocalTimeValue(IsoMessage isoMessage, int index) {

		Date date = getDateValue(isoMessage, index);
		if (date != null) {

			LocalTime localDateTime = DateUtil.convertToLocalTime( date);
			return localDateTime;
		}
		return null;
	}

	private Double convertAmt(String amount) {

		if (StringUtil.isNotNullNorEmpty(amount)) {
			String amtStr = amount.substring(0, amount.length() - 2) + "."
									+ amount.substring(amount.length() - 2);
			return Double.parseDouble(amtStr);
		}

		return Double.parseDouble("0");
	}

	private void processOriginalDataElement(ReversalMessage reversalMessage, String orgDataEle) {

		if (StringUtil.isNotNullNorEmpty(orgDataEle) && orgDataEle.length() == 43) {

			String mti = orgDataEle.substring(0, 4);
			reversalMessage.setOriginalTypeIdentification(MessageTypeIdentification.forMti(mti));
			String stan = orgDataEle.substring(4, 10);
			reversalMessage.setOrgStan(stan);
			String transDateTime = orgDataEle.substring(10, 20);
			//sreversalMessage.setOrgTransmissionDateAndTime(DateUtil.parse(transDateTime, DateUtil.ORG_TRANSAMISSION_DATE_TIME_FORMAT));
			String acquirerId = orgDataEle.substring(20, 31);
			reversalMessage.setOrgAcquirerIdentification(acquirerId);
			String retRefno = orgDataEle.substring(31);
			reversalMessage.setOrgRetRefNo(retRefno);
		}
	}
}
