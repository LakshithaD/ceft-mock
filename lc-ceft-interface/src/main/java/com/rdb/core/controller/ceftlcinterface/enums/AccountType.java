package com.rdb.core.controller.ceftlcinterface.enums;

public enum AccountType {

	UNSPECIFIED("00"), SAVING("10"), CHECKING("20"), CREDIT_CARD("30");

	private String code;

	private AccountType(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public static AccountType forCode(String code) {

		for (AccountType accountType : AccountType.values()) {

			if (accountType.getCode().equalsIgnoreCase(code)) {

				return accountType;
			}
		}
		return null;
	}

}
