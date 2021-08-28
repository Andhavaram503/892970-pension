package com.example.demo.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.Assert;

@SpringBootTest
class BankEntityTest {

	public static final String EXPECTED_BANK_NAME = "HDFC";
    public static final String EXPECTED_BANK_TYPE = "private";
    public static final long EXPECTED_ACCOUNT_NUMBER = 1232456789;

    @Test
    void testUserDetails() throws Exception {
    	Bank bank = new Bank("HDFC",1232456789,"private");
        Assert.assertEquals(EXPECTED_BANK_NAME, bank.getBankName());
        Assert.assertEquals(EXPECTED_BANK_TYPE, bank.getBankType());
        Assert.assertEquals(EXPECTED_ACCOUNT_NUMBER, bank.getAccountNo());

    }
}
