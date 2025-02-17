/*
 * This file is generated by jOOQ.
 */
package com.Prog_3_Projektarbeit.generated.tables;


import com.Prog_3_Projektarbeit.generated.DefaultSchema;
import com.Prog_3_Projektarbeit.generated.Keys;
import com.Prog_3_Projektarbeit.generated.tables.Budget.BudgetPath;
import com.Prog_3_Projektarbeit.generated.tables.User.UserPath;
import com.Prog_3_Projektarbeit.generated.tables.records.TransactionsRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
public class Transactions extends TableImpl<TransactionsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>Transactions</code>
     */
    public static final Transactions TRANSACTIONS = new Transactions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TransactionsRecord> getRecordType() {
        return TransactionsRecord.class;
    }

    /**
     * The column <code>Transactions.transaction_id</code>.
     */
    public final TableField<TransactionsRecord, Integer> TRANSACTION_ID = createField(DSL.name("transaction_id"), SQLDataType.INTEGER.identity(true), this, "");

    /**
     * The column <code>Transactions.transaction_Name</code>.
     */
    public final TableField<TransactionsRecord, String> TRANSACTION_NAME = createField(DSL.name("transaction_Name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>Transactions.budget_id</code>.
     */
    public final TableField<TransactionsRecord, Integer> BUDGET_ID = createField(DSL.name("budget_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>Transactions.user_id</code>.
     */
    public final TableField<TransactionsRecord, String> USER_ID = createField(DSL.name("user_id"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>Transactions.category</code>.
     */
    public final TableField<TransactionsRecord, String> CATEGORY = createField(DSL.name("category"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>Transactions.ammount</code>.
     */
    public final TableField<TransactionsRecord, Float> AMMOUNT = createField(DSL.name("ammount"), SQLDataType.REAL.nullable(false), this, "");

    /**
     * The column <code>Transactions.date</code>.
     */
    public final TableField<TransactionsRecord, LocalDate> DATE = createField(DSL.name("date"), SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>Transactions.description</code>.
     */
    public final TableField<TransactionsRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");

    private Transactions(Name alias, Table<TransactionsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Transactions(Name alias, Table<TransactionsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>Transactions</code> table reference
     */
    public Transactions(String alias) {
        this(DSL.name(alias), TRANSACTIONS);
    }

    /**
     * Create an aliased <code>Transactions</code> table reference
     */
    public Transactions(Name alias) {
        this(alias, TRANSACTIONS);
    }

    /**
     * Create a <code>Transactions</code> table reference
     */
    public Transactions() {
        this(DSL.name("Transactions"), null);
    }

    public <O extends Record> Transactions(Table<O> path, ForeignKey<O, TransactionsRecord> childPath, InverseForeignKey<O, TransactionsRecord> parentPath) {
        super(path, childPath, parentPath, TRANSACTIONS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class TransactionsPath extends Transactions implements Path<TransactionsRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> TransactionsPath(Table<O> path, ForeignKey<O, TransactionsRecord> childPath, InverseForeignKey<O, TransactionsRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private TransactionsPath(Name alias, Table<TransactionsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public TransactionsPath as(String alias) {
            return new TransactionsPath(DSL.name(alias), this);
        }

        @Override
        public TransactionsPath as(Name alias) {
            return new TransactionsPath(alias, this);
        }

        @Override
        public TransactionsPath as(Table<?> alias) {
            return new TransactionsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<TransactionsRecord, Integer> getIdentity() {
        return (Identity<TransactionsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<TransactionsRecord> getPrimaryKey() {
        return Keys.TRANSACTIONS__PK_TRANSACTIONS;
    }

    @Override
    public List<ForeignKey<TransactionsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.TRANSACTIONS__FK_TRANSACTIONS_PK_BUDGET, Keys.TRANSACTIONS__FK_TRANSACTIONS_PK_USER);
    }

    private transient BudgetPath _budget;

    /**
     * Get the implicit join path to the <code>budget</code> table.
     */
    public BudgetPath budget() {
        if (_budget == null)
            _budget = new BudgetPath(this, Keys.TRANSACTIONS__FK_TRANSACTIONS_PK_BUDGET, null);

        return _budget;
    }

    private transient UserPath _user;

    /**
     * Get the implicit join path to the <code>User</code> table.
     */
    public UserPath user() {
        if (_user == null)
            _user = new UserPath(this, Keys.TRANSACTIONS__FK_TRANSACTIONS_PK_USER, null);

        return _user;
    }

    @Override
    public Transactions as(String alias) {
        return new Transactions(DSL.name(alias), this);
    }

    @Override
    public Transactions as(Name alias) {
        return new Transactions(alias, this);
    }

    @Override
    public Transactions as(Table<?> alias) {
        return new Transactions(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Transactions rename(String name) {
        return new Transactions(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Transactions rename(Name name) {
        return new Transactions(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Transactions rename(Table<?> name) {
        return new Transactions(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions where(Condition condition) {
        return new Transactions(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Transactions where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Transactions where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Transactions where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Transactions where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
