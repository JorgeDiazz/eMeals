package com.jorgediaz.emeals.datasources.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jorgediaz.emeals.datasources.room.RecipeRoom;
import com.jorgediaz.emeals.datasources.room.RecipesDao;

@Database(version = 1, entities = {RecipeRoom.class}, exportSchema = false)
public abstract class RecipesDatabase extends RoomDatabase {

    public abstract RecipesDao getRecipesDao();
}
