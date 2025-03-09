package model;

import org.jooq.DSLContext;

import java.util.List;

import static com.Prog_3_Projektarbeit.generated.tables.Have.HAVE;

// Klasse löscht Einträge aus der Tabelle "Have" anhand der BudgetId
public class HaveModel {
    private final DSLContext dslContext;

    public HaveModel(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void deleteByBudgetId(int budgetId) {
        dslContext.deleteFrom(HAVE)
                .where(HAVE.BUDGET_ID.eq(budgetId))
                .execute();
    }



    public List<String> getAllUsersforBudget(int budgetId) {
        return dslContext.select(HAVE.USER_NAME)
                .from(HAVE)
                .where(HAVE.BUDGET_ID.eq(budgetId))
                .fetch(HAVE.USER_NAME);
    }
}