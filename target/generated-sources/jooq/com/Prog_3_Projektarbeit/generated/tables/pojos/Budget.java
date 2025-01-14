/*
 * This file is generated by jOOQ.
 */
package com.Prog_3_Projektarbeit.generated.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Budget implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object budgetId;
    private String budgetName;
    private Float ammount;
    private LocalDateTime createdAt;

    public Budget() {}

    public Budget(Budget value) {
        this.budgetId = value.budgetId;
        this.budgetName = value.budgetName;
        this.ammount = value.ammount;
        this.createdAt = value.createdAt;
    }

    public Budget(
        Object budgetId,
        String budgetName,
        Float ammount,
        LocalDateTime createdAt
    ) {
        this.budgetId = budgetId;
        this.budgetName = budgetName;
        this.ammount = ammount;
        this.createdAt = createdAt;
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public Object getBudgetId() {
        return this.budgetId;
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public void setBudgetId(Object budgetId) {
        this.budgetId = budgetId;
    }

    /**
     * Getter for <code>Budget.budget_name</code>.
     */
    public String getBudgetName() {
        return this.budgetName;
    }

    /**
     * Setter for <code>Budget.budget_name</code>.
     */
    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    /**
     * Getter for <code>Budget.ammount</code>.
     */
    public Float getAmmount() {
        return this.ammount;
    }

    /**
     * Setter for <code>Budget.ammount</code>.
     */
    public void setAmmount(Float ammount) {
        this.ammount = ammount;
    }

    /**
     * Getter for <code>Budget.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>Budget.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Budget other = (Budget) obj;
        if (this.budgetId == null) {
            if (other.budgetId != null)
                return false;
        }
        else if (!this.budgetId.equals(other.budgetId))
            return false;
        if (this.budgetName == null) {
            if (other.budgetName != null)
                return false;
        }
        else if (!this.budgetName.equals(other.budgetName))
            return false;
        if (this.ammount == null) {
            if (other.ammount != null)
                return false;
        }
        else if (!this.ammount.equals(other.ammount))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!this.createdAt.equals(other.createdAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.budgetId == null) ? 0 : this.budgetId.hashCode());
        result = prime * result + ((this.budgetName == null) ? 0 : this.budgetName.hashCode());
        result = prime * result + ((this.ammount == null) ? 0 : this.ammount.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Budget (");

        sb.append(budgetId);
        sb.append(", ").append(budgetName);
        sb.append(", ").append(ammount);
        sb.append(", ").append(createdAt);

        sb.append(")");
        return sb.toString();
    }
}
