package com.estsoft.springdemoproject.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.fail;

// TDD
// 1. 계좌 생성 (v)
// 2. 잔금 조회
// 3. 입/출금
public class AccountTest {
    Account account;

    @BeforeEach
    public void setUp() {
        account = new Account(10000);

    }

    @Test
    public void testAccount() {
        // 잔금 조회가 잘 되나?
        // hamcrest
        assertThat(account.getBalance(), is(10000));

        // AssertJ
        assertEquals(10000, account.getBalance());

        if(account.getBalance() != 10000) {
            fail();
        }

        Account account2 = new Account(200000);
        // 잔금 조회가 잘 되나?
        if(account2.getBalance() != 200000) {
            fail();
        }
    }

    @Test
    public void testDeposit() {
        account.deposit(500000);
        assertThat(account.getBalance(), is(10000+500000));
    }

    @Test
    public void testWithdraw() {
        account.withdraw(10000);
        assertThat(account.getBalance(), is(10000-10000));
    }
}
