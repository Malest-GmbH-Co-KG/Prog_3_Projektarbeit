/*
 * This file is generated by jOOQ.
 */
package com.Prog_3_Projektarbeit.generated.tables.daos;


import com.Prog_3_Projektarbeit.generated.tables.Users;
import com.Prog_3_Projektarbeit.generated.tables.records.UsersRecord;

import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class UsersDao extends DAOImpl<UsersRecord, com.Prog_3_Projektarbeit.generated.tables.pojos.Users, Integer> {

    /**
     * Create a new UsersDao without any configuration
     */
    public UsersDao() {
        super(Users.USERS, com.Prog_3_Projektarbeit.generated.tables.pojos.Users.class);
    }

    /**
     * Create a new UsersDao with an attached configuration
     */
    public UsersDao(Configuration configuration) {
        super(Users.USERS, com.Prog_3_Projektarbeit.generated.tables.pojos.Users.class, configuration);
    }

    @Override
    public Integer getId(com.Prog_3_Projektarbeit.generated.tables.pojos.Users object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Users> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Users.USERS.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Users> fetchById(Integer... values) {
        return fetch(Users.USERS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.Prog_3_Projektarbeit.generated.tables.pojos.Users fetchOneById(Integer value) {
        return fetchOne(Users.USERS.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<com.Prog_3_Projektarbeit.generated.tables.pojos.Users> fetchOptionalById(Integer value) {
        return fetchOptional(Users.USERS.ID, value);
    }

    /**
     * Fetch records that have <code>username BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Users> fetchRangeOfUsername(String lowerInclusive, String upperInclusive) {
        return fetchRange(Users.USERS.USERNAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>username IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Users> fetchByUsername(String... values) {
        return fetch(Users.USERS.USERNAME, values);
    }

    /**
     * Fetch a unique record that has <code>username = value</code>
     */
    public com.Prog_3_Projektarbeit.generated.tables.pojos.Users fetchOneByUsername(String value) {
        return fetchOne(Users.USERS.USERNAME, value);
    }

    /**
     * Fetch a unique record that has <code>username = value</code>
     */
    public Optional<com.Prog_3_Projektarbeit.generated.tables.pojos.Users> fetchOptionalByUsername(String value) {
        return fetchOptional(Users.USERS.USERNAME, value);
    }

    /**
     * Fetch records that have <code>password BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Users> fetchRangeOfPassword(String lowerInclusive, String upperInclusive) {
        return fetchRange(Users.USERS.PASSWORD, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<com.Prog_3_Projektarbeit.generated.tables.pojos.Users> fetchByPassword(String... values) {
        return fetch(Users.USERS.PASSWORD, values);
    }
}
