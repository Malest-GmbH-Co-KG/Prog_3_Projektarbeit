/*
 * This file is generated by jOOQ.
 */
package com.Prog_3_Projektarbeit.generated.tables.records;


import com.Prog_3_Projektarbeit.generated.tables.Accounts;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class AccountsRecord extends UpdatableRecordImpl<AccountsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>accounts.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>accounts.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>accounts.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>accounts.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>accounts.account_name</code>.
     */
    public void setAccountName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>accounts.account_name</code>.
     */
    public String getAccountName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>accounts.balance</code>.
     */
    public void setBalance(Float value) {
        set(3, value);
    }

    /**
     * Getter for <code>accounts.balance</code>.
     */
    public Float getBalance() {
        return (Float) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountsRecord
     */
    public AccountsRecord() {
        super(Accounts.ACCOUNTS);
    }

    /**
     * Create a detached, initialised AccountsRecord
     */
    public AccountsRecord(Integer id, Integer userId, String accountName, Float balance) {
        super(Accounts.ACCOUNTS);

        setId(id);
        setUserId(userId);
        setAccountName(accountName);
        setBalance(balance);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised AccountsRecord
     */
    public AccountsRecord(com.Prog_3_Projektarbeit.generated.tables.pojos.Accounts value) {
        super(Accounts.ACCOUNTS);

        if (value != null) {
            setId(value.getId());
            setUserId(value.getUserId());
            setAccountName(value.getAccountName());
            setBalance(value.getBalance());
            resetChangedOnNotNull();
        }
    }
}
