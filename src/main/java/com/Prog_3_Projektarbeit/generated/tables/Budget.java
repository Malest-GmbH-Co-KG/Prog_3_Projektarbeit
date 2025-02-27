/*
 * This file is generated by jOOQ.
 */
package com.Prog_3_Projektarbeit.generated.tables;


import com.Prog_3_Projektarbeit.generated.DefaultSchema;
import com.Prog_3_Projektarbeit.generated.Keys;
import com.Prog_3_Projektarbeit.generated.tables.Have.HavePath;
import com.Prog_3_Projektarbeit.generated.tables.Transactions.TransactionsPath;
import com.Prog_3_Projektarbeit.generated.tables.User.UserPath;
import com.Prog_3_Projektarbeit.generated.tables.records.BudgetRecord;

import java.time.LocalDateTime;
import java.util.Collection;

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
public class Budget extends TableImpl<BudgetRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>budget</code>
     */
    public static final Budget BUDGET = new Budget();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BudgetRecord> getRecordType() {
        return BudgetRecord.class;
    }

    /**
     * The column <code>budget.budget_id</code>.
     */
    public final TableField<BudgetRecord, Integer> BUDGET_ID = createField(DSL.name("budget_id"), SQLDataType.INTEGER.identity(true), this, "");

    /**
     * The column <code>budget.budget_name</code>.
     */
    public final TableField<BudgetRecord, String> BUDGET_NAME = createField(DSL.name("budget_name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>budget.ammount</code>.
     */
    public final TableField<BudgetRecord, Float> AMMOUNT = createField(DSL.name("ammount"), SQLDataType.REAL.nullable(false), this, "");

    /**
     * The column <code>budget.allTransactionAmmount</code>.
     */
    public final TableField<BudgetRecord, Float> ALLTRANSACTIONAMMOUNT = createField(DSL.name("allTransactionAmmount"), SQLDataType.REAL, this, "");

    /**
     * The column <code>budget.created_at</code>.
     */
    public final TableField<BudgetRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    private Budget(Name alias, Table<BudgetRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Budget(Name alias, Table<BudgetRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>budget</code> table reference
     */
    public Budget(String alias) {
        this(DSL.name(alias), BUDGET);
    }

    /**
     * Create an aliased <code>budget</code> table reference
     */
    public Budget(Name alias) {
        this(alias, BUDGET);
    }

    /**
     * Create a <code>budget</code> table reference
     */
    public Budget() {
        this(DSL.name("budget"), null);
    }

    public <O extends Record> Budget(Table<O> path, ForeignKey<O, BudgetRecord> childPath, InverseForeignKey<O, BudgetRecord> parentPath) {
        super(path, childPath, parentPath, BUDGET);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class BudgetPath extends Budget implements Path<BudgetRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> BudgetPath(Table<O> path, ForeignKey<O, BudgetRecord> childPath, InverseForeignKey<O, BudgetRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private BudgetPath(Name alias, Table<BudgetRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public BudgetPath as(String alias) {
            return new BudgetPath(DSL.name(alias), this);
        }

        @Override
        public BudgetPath as(Name alias) {
            return new BudgetPath(alias, this);
        }

        @Override
        public BudgetPath as(Table<?> alias) {
            return new BudgetPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<BudgetRecord, Integer> getIdentity() {
        return (Identity<BudgetRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<BudgetRecord> getPrimaryKey() {
        return Keys.BUDGET__PK_BUDGET;
    }

    private transient HavePath _have;

    /**
     * Get the implicit to-many join path to the <code>Have</code> table
     */
    public HavePath have() {
        if (_have == null)
            _have = new HavePath(this, null, Keys.HAVE__FK_HAVE_PK_BUDGET.getInverseKey());

        return _have;
    }

    private transient TransactionsPath _transactions;

    /**
     * Get the implicit to-many join path to the <code>Transactions</code> table
     */
    public TransactionsPath transactions() {
        if (_transactions == null)
            _transactions = new TransactionsPath(this, null, Keys.TRANSACTIONS__FK_TRANSACTIONS_PK_BUDGET.getInverseKey());

        return _transactions;
    }

    /**
     * Get the implicit many-to-many join path to the <code>User</code> table
     */
    public UserPath user() {
        return have().user();
    }

    @Override
    public Budget as(String alias) {
        return new Budget(DSL.name(alias), this);
    }

    @Override
    public Budget as(Name alias) {
        return new Budget(alias, this);
    }

    @Override
    public Budget as(Table<?> alias) {
        return new Budget(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Budget rename(String name) {
        return new Budget(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Budget rename(Name name) {
        return new Budget(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Budget rename(Table<?> name) {
        return new Budget(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Budget where(Condition condition) {
        return new Budget(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Budget where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Budget where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Budget where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Budget where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Budget where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Budget where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Budget where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Budget whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Budget whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
