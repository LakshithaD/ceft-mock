package com.rdb.core.controller.ceftlcinterface.enums;

public enum MessageTypeIdentification {

	NETWORK_MGT_REQ( 2048, "0800"), NETWORK_MGT_RESPONSE( 2064, "0810"), FINANCIAL_REQ( 512, "0200"),
	FINANCIAL_RESPONSE( 528, "0200"), REVERSAL_REQ( 1056, "0420"), REVERSAL_RESPONSE( 1072, "0430"),
	REVERSAL_ADVICE_REQ(1057, "0421"), REVERSAL_CANCEL(1056, "0420");

	private int val;

	private String mti;

	private MessageTypeIdentification(int val, String mti) {

		this.val = val;
		this.mti = mti;
	}

	public int getVal() {
		return val;
	}

	public String getMti() {
		return mti;
	}

	public static MessageTypeIdentification forMti(String mti) {

		for (MessageTypeIdentification curMti : MessageTypeIdentification.values()) {

			if (curMti.getMti().equalsIgnoreCase(mti)) {

				return curMti;
			}
		}
		return null;
	}

	public static MessageTypeIdentification forVal(int val) {

		for (MessageTypeIdentification curVal : MessageTypeIdentification.values()) {

			if (curVal.getVal() == val) {

				return curVal;
			}
		}
		return null;
	}
}
