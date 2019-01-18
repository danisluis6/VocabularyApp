package com.tienganh.vocabulary.storage.dbaccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.tienganh.vocabulary.storage.DbContract;
import com.tienganh.vocabulary.storage.DbHelper;
import com.tienganh.vocabulary.storage.entities.Recipe;
import com.tienganh.vocabulary.utilities.Constants;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class DARecipe {

    private ContentValues getContentValues(final Recipe recipe, Context context) {
        ContentValues values = new ContentValues();
        values.put(DbContract.TableRecipe.COLUMN_NAME_RECIPE, recipe.getName());
        values.put(DbContract.TableRecipe.COLUMN_NAME_INGREDIENTS, recipe.getIngredients());
        values.put(DbContract.TableRecipe.COLUMN_NAME_DESCRIPTION, recipe.getDescription());
        values.put(DbContract.TableRecipe.COLUMN_NAME_PORTIONS, recipe.getPortions());
        values.put(DbContract.TableRecipe.COLUMN_NAME_TERMS, recipe.getTerms());
        values.put(DbContract.TableRecipe.COLUMN_NAME_TYPE, recipe.getRecipeTypes());
        values.put(DbContract.TableRecipe.COLUMN_NAME_REFER, recipe.getRefer());
        return values;
    }

    private Recipe getFromCursor(final Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(DbContract.TableRecipe.COLUMN_NAME_INDEX));
        String name = cursor.getString(cursor.getColumnIndex(DbContract.TableRecipe.COLUMN_NAME_RECIPE));
        String description = cursor.getString(cursor.getColumnIndex(DbContract.TableRecipe.COLUMN_NAME_DESCRIPTION));
        String ingredients = cursor.getString(cursor.getColumnIndex(DbContract.TableRecipe.COLUMN_NAME_INGREDIENTS));
        int portions = cursor.getInt(cursor.getColumnIndex(DbContract.TableRecipe.COLUMN_NAME_PORTIONS));
        String terms = cursor.getString(cursor.getColumnIndex(DbContract.TableRecipe.COLUMN_NAME_TERMS));
        String refer = cursor.getString(cursor.getColumnIndex(DbContract.TableRecipe.COLUMN_NAME_REFER));
        String type = cursor.getString(cursor.getColumnIndex(DbContract.TableRecipe.COLUMN_NAME_TYPE));
        return new Recipe(id, name, ingredients, description, portions, terms, refer, type);
    }

    public long add(Recipe recipe, Context context) {
        long id = 0;
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getContentValues(recipe, context);
        if (db != null && db.isOpen()) {
            id = db.insert(DbContract.TableRecipe.TABLE_NAME, null, values);
        }
        return id;
    }

    public boolean edit(Recipe recipe, Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbContract.TableRecipe.COLUMN_NAME_RECIPE, recipe.getName());
        cv.put(DbContract.TableRecipe.COLUMN_NAME_DESCRIPTION, recipe.getDescription());
        cv.put(DbContract.TableRecipe.COLUMN_NAME_INGREDIENTS, recipe.getIngredients());
        cv.put(DbContract.TableRecipe.COLUMN_NAME_PORTIONS, recipe.getPortions());
        cv.put(DbContract.TableRecipe.COLUMN_NAME_TERMS, recipe.getTerms());
        cv.put(DbContract.TableRecipe.COLUMN_NAME_REFER, recipe.getTerms());
        cv.put(DbContract.TableRecipe.COLUMN_NAME_TYPE, recipe.getRecipeTypes());
        try {
            db.update(DbContract.TableRecipe.TABLE_NAME, cv, DbContract.TableRecipe.COLUMN_NAME_INDEX + "=" + recipe.getId(), null);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private String[] getProjection() {
        return new String[]{
                DbContract.TableRecipe.COLUMN_NAME_INDEX,
                DbContract.TableRecipe.COLUMN_NAME_RECIPE,
                DbContract.TableRecipe.COLUMN_NAME_DESCRIPTION,
                DbContract.TableRecipe.COLUMN_NAME_INGREDIENTS,
                DbContract.TableRecipe.COLUMN_NAME_PORTIONS,
                DbContract.TableRecipe.COLUMN_NAME_TERMS,
                DbContract.TableRecipe.COLUMN_NAME_REFER,
                DbContract.TableRecipe.COLUMN_NAME_TYPE
        };
    }

    public ArrayList<Recipe> getAll(Context context) {
        ArrayList<Recipe> listRecipes = new ArrayList<>();
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (db != null && db.isOpen()) {
            Cursor cursor = db.query(DbContract.TableRecipe.TABLE_NAME, getProjection(),
                    null, null, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Recipe recipe;
                do {
                    recipe = getFromCursor(cursor);
                    listRecipes.add(recipe);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return listRecipes;
    }


    public boolean deleteById(int index, Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = new StringBuilder(Constants.EMPTY_STRING)
                .append(DbContract.TableRecipe.COLUMN_NAME_INDEX).append(Constants.EQUAL)
                .toString();
        String[] selectionArgs = {String.valueOf(index)};
        return db != null && db.isOpen()
                && db.delete(DbContract.TableRecipe.TABLE_NAME, selection, selectionArgs) > 0;
    }

}
