package com.example.demo.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Bank {

	private String bankName;

	private long accountNo;

	private String bankType;

	public String getBankName() {
		return bankName;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public String getBankType() {
		return bankType;
	}

}
