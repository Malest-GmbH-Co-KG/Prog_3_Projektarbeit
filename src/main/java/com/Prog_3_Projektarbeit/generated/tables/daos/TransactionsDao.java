/*
 * This file is generated by jOOQ.
 */
package com.Prog_3_Projektarbeit.generated.tables.daos;


import com.Prog_3_Projektarbeit.generated.tables.Transactions;
import com.Prog_3_Projektarbeit.generated.tables.records.TransactionsRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class TransactionsDao extends DAOImpl<TransactionsRecord, com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions, Integer> {

    /**
     * Create a new TransactionsDao without any configuration
     */
    public TransactionsDao() {
        super(Transactions.TRANSACTIONS, com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions.class);
    }

    /**
     * Create a new TransactionsDao with an attached configuration
     */
    public TransactionsDao(Configuration configuration) {
        super(Transactions.TRANSACTIONS, com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions.class, configuration);
    }

    @Override
    public Integer getId(com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions object) {
        return object.getTransactionId();
    }

    /**
     * Fetch records that have <code>transaction_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchRangeOfTransactionId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Transactions.TRANSACTIONS.TRANSACTION_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>transaction_id IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchByTransactionId(Integer... values) {
        return fetch(Transactions.TRANSACTIONS.TRANSACTION_ID, values);
    }

    /**
     * Fetch a unique record that has <code>transaction_id = value</code>
     */
    public com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions fetchOneByTransactionId(Integer value) {
        return fetchOne(Transactions.TRANSACTIONS.TRANSACTION_ID, value);
    }

    /**
     * Fetch a unique record that has <code>transaction_id = value</code>
     */
    public Optional<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchOptionalByTransactionId(Integer value) {
        return fetchOptional(Transactions.TRANSACTIONS.TRANSACTION_ID, value);
    }

    /**
     * Fetch records that have <code>transaction_Name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchRangeOfTransactionName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Transactions.TRANSACTIONS.TRANSACTION_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>transaction_Name IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchByTransactionName(String... values) {
        return fetch(Transactions.TRANSACTIONS.TRANSACTION_NAME, values);
    }

    /**
     * Fetch records that have <code>budget_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchRangeOfBudgetId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Transactions.TRANSACTIONS.BUDGET_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>budget_id IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchByBudgetId(Integer... values) {
        return fetch(Transactions.TRANSACTIONS.BUDGET_ID, values);
    }

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchRangeOfUserId(String lowerInclusive, String upperInclusive) {
        return fetchRange(Transactions.TRANSACTIONS.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchByUserId(String... values) {
        return fetch(Transactions.TRANSACTIONS.USER_ID, values);
    }

    /**
     * Fetch records that have <code>category BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchRangeOfCategory(String lowerInclusive, String upperInclusive) {
        return fetchRange(Transactions.TRANSACTIONS.CATEGORY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>category IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchByCategory(String... values) {
        return fetch(Transactions.TRANSACTIONS.CATEGORY, values);
    }

    /**
     * Fetch records that have <code>ammount BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchRangeOfAmmount(Float lowerInclusive, Float upperInclusive) {
        return fetchRange(Transactions.TRANSACTIONS.AMMOUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>ammount IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchByAmmount(Float... values) {
        return fetch(Transactions.TRANSACTIONS.AMMOUNT, values);
    }

    /**
     * Fetch records that have <code>date BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchRangeOfDate(LocalDate lowerInclusive, LocalDate upperInclusive) {
        return fetchRange(Transactions.TRANSACTIONS.DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>date IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchByDate(LocalDate... values) {
        return fetch(Transactions.TRANSACTIONS.DATE, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(Transactions.TRANSACTIONS.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions> fetchByDescription(String... values) {
        return fetch(Transactions.TRANSACTIONS.DESCRIPTION, values);
    }
}
