package com.rdb.core.controller.ceftlcinterface.enums;

public enum NetworkMgtCode {

	ECHO_TEST("301"), LOG_ON("001"), LOG_OFF("002"), KEY_MESSAGE_MEMBER_BANK("161"), KEY_MESSAGE_LC("162"), CUTOFF("203");

	String code;

	private NetworkMgtCode(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public static NetworkMgtCode forCode(String code) {

		for (NetworkMgtCode networkMgtCode : NetworkMgtCode.values()) {

			if (networkMgtCode.getCode().equalsIgnoreCase(code)) {

				return networkMgtCode;
			}
		}
		return null;
	}

}
