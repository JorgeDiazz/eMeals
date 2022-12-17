package com.jorgediaz.emeals.datasources.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class RecipeRoom {

    public RecipeRoom(int id) {
        this.id = id;
    }

    @PrimaryKey
    public int id;
}
