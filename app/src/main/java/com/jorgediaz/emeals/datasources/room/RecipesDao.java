package com.jorgediaz.emeals.datasources.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

/**
 * Represents Room dao for recipe entity.
 */
@Dao
public interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<RecipeRoom> recipes);

    @Query("SELECT * FROM recipes")
    Observable<List<RecipeRoom>> getRecipes();

    @Query("UPDATE recipes SET title = :newTitle WHERE id = :recipeId")
    Completable updateRecipeTitle(int recipeId, String newTitle);
}
