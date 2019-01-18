package com.tienganh.vocabulary.storage.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class Recipe implements Parcelable {

    /**
     * Index of dish
     */
    private int id;

    /**
     * Name of the dish
     */
    private String name;

    /**
     * List of ingredients
     */
    private String ingredients;

    /**
     * Description of how the dish should be prepared and completed
     */
    private String description;

    /**
     * Total amount or number of portions
     */
    private int portions;

    /**
     * Explanation of Culinary terms
     */
    private String terms;

    /**
     * These documents, instructions or method from other resources
     */
    private String refer;

    /**
     * Type recipetypes
     */
    private String recipeTypes;

    public Recipe() {
    }

    public Recipe(int id, String name, String ingredients, String description, int portions, String terms, String refer, String recipeTypes) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.portions = portions;
        this.terms = terms;
        this.refer = refer;
        this.recipeTypes = recipeTypes;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = in.readString();
        description = in.readString();
        portions = in.readInt();
        terms = in.readString();
        refer = in.readString();
        recipeTypes = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getRecipeTypes() {
        return recipeTypes;
    }

    public void setRecipeTypes(String recipeTypes) {
        this.recipeTypes = recipeTypes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(ingredients);
        dest.writeString(description);
        dest.writeInt(portions);
        dest.writeString(terms);
        dest.writeString(refer);
        dest.writeString(recipeTypes);
    }
}
