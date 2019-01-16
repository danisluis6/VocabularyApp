package recipe.android.vogorecipe.storage;

import android.provider.BaseColumns;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public final class DbContract {
    /**
     * Text data type.
     */
    private static final String TEXT_TYPE = " TEXT";
    /**
     * Integer data type.
     */
    private static final String INTEGER_TYPE = " INTEGER";
    /**
     * Comma symbol.
     */
    private static final String COMMA_SEP = ",";
    /**
     * Left bracket symbol.
     */
    private static final String LEFT_BRACKET_SEP = " (";
    /**
     * Right bracket symbol.
     */
    private static final String RIGHT_BRACKET_SEP = " );";
    /**
     * Primary key.
     */
    private static final String PRIMARY_AUTOINCREMENT = " PRIMARY KEY AUTOINCREMENT";
    /**
     * Create table statement.
     */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    /**
     * Create query for WORD table.
     */
    static final String SQL_CREATE_SCHEDULE = new StringBuilder(CREATE_TABLE)
            .append(TableRecipe.TABLE_NAME).append(LEFT_BRACKET_SEP)
            .append(TableRecipe.COLUMN_NAME_INDEX).append(INTEGER_TYPE)
            .append(PRIMARY_AUTOINCREMENT)
            .append(COMMA_SEP)
            .append(TableRecipe.COLUMN_NAME_RECIPE).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(TableRecipe.COLUMN_NAME_INGREDIENTS).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(TableRecipe.COLUMN_NAME_DESCRIPTION).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(TableRecipe.COLUMN_NAME_PORTIONS).append(INTEGER_TYPE)
            .append(COMMA_SEP)
            .append(TableRecipe.COLUMN_NAME_TERMS).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(TableRecipe.COLUMN_NAME_TYPE).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(TableRecipe.COLUMN_NAME_REFER).append(TEXT_TYPE)
            .append(RIGHT_BRACKET_SEP)
            .toString();
    /**
     * Drop table statement.
     */
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    /**
     * Drop query for User table.
     */
    static final String SQL_DELETE_SCHEDULE = new StringBuilder(DROP_TABLE)
            .append(TableRecipe.TABLE_NAME).toString();

    /**
     * Constructor. Prevents the DbUser class from being instantiated.
     */
    private DbContract() {
    }

    public abstract static class TableRecipe implements BaseColumns {
        public static final String TABLE_NAME = "recipe";
        public static final String COLUMN_NAME_INDEX = "id";
        public static final String COLUMN_NAME_RECIPE = "name";
        public static final String COLUMN_NAME_INGREDIENTS = "ingredients";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PORTIONS = "portions";
        public static final String COLUMN_NAME_TERMS = "terms";
        public static final String COLUMN_NAME_REFER = "refer";
        public static final String COLUMN_NAME_TYPE = "type";
    }
}
