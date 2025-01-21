package model;

import org.jooq.DSLContext;
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
}