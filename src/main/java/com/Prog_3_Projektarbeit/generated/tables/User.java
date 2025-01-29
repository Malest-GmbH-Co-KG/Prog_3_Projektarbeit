/*
 * This file is generated by jOOQ.
 */
package com.Prog_3_Projektarbeit.generated.tables;


import com.Prog_3_Projektarbeit.generated.DefaultSchema;
import com.Prog_3_Projektarbeit.generated.Keys;
import com.Prog_3_Projektarbeit.generated.tables.Budget.BudgetPath;
import com.Prog_3_Projektarbeit.generated.tables.Have.HavePath;
import com.Prog_3_Projektarbeit.generated.tables.Transactions.TransactionsPath;
import com.Prog_3_Projektarbeit.generated.tables.records.UserRecord;

import java.time.LocalDateTime;
import java.util.Collection;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class User extends TableImpl<UserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>User</code>
     */
    public static final User USER = new User();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * The column <code>User.user_name</code>.
     */
    public final TableField<UserRecord, String> USER_NAME = createField(DSL.name("user_name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>User.Vorname</code>.
     */
    public final TableField<UserRecord, String> VORNAME = createField(DSL.name("Vorname"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>User.Nachname</code>.
     */
    public final TableField<UserRecord, String> NACHNAME = createField(DSL.name("Nachname"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>User.password</code>.
     */
    public final TableField<UserRecord, String> PASSWORD = createField(DSL.name("password"), SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>User.salt</code>.
     */
    public final TableField<UserRecord, String> SALT = createField(DSL.name("salt"), SQLDataType.VARCHAR(256), this, "");

    /**
     * The column <code>User.created_at</code>.
     */
    public final TableField<UserRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    private User(Name alias, Table<UserRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private User(Name alias, Table<UserRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>User</code> table reference
     */
    public User(String alias) {
        this(DSL.name(alias), USER);
    }

    /**
     * Create an aliased <code>User</code> table reference
     */
    public User(Name alias) {
        this(alias, USER);
    }

    /**
     * Create a <code>User</code> table reference
     */
    public User() {
        this(DSL.name("User"), null);
    }

    public <O extends Record> User(Table<O> path, ForeignKey<O, UserRecord> childPath, InverseForeignKey<O, UserRecord> parentPath) {
        super(path, childPath, parentPath, USER);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class UserPath extends User implements Path<UserRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> UserPath(Table<O> path, ForeignKey<O, UserRecord> childPath, InverseForeignKey<O, UserRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private UserPath(Name alias, Table<UserRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public UserPath as(String alias) {
            return new UserPath(DSL.name(alias), this);
        }

        @Override
        public UserPath as(Name alias) {
            return new UserPath(alias, this);
        }

        @Override
        public UserPath as(Table<?> alias) {
            return new UserPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.USER__PK_USER;
    }

    private transient HavePath _have;

    /**
     * Get the implicit to-many join path to the <code>Have</code> table
     */
    public HavePath have() {
        if (_have == null)
            _have = new HavePath(this, null, Keys.HAVE__FK_HAVE_PK_USER.getInverseKey());

        return _have;
    }

    private transient TransactionsPath _transactions;

    /**
     * Get the implicit to-many join path to the <code>Transactions</code> table
     */
    public TransactionsPath transactions() {
        if (_transactions == null)
            _transactions = new TransactionsPath(this, null, Keys.TRANSACTIONS__FK_TRANSACTIONS_PK_USER.getInverseKey());

        return _transactions;
    }

    /**
     * Get the implicit many-to-many join path to the <code>budget</code> table
     */
    public BudgetPath budget() {
        return have().budget();
    }

    @Override
    public User as(String alias) {
        return new User(DSL.name(alias), this);
    }

    @Override
    public User as(Name alias) {
        return new User(alias, this);
    }

    @Override
    public User as(Table<?> alias) {
        return new User(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Name name) {
        return new User(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Table<?> name) {
        return new User(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Condition condition) {
        return new User(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
