package com.jorgediaz.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jorgediaz.presentation.ui.RecipesFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RecipesFragment.newInstance())
                    .commitNow();
        }
    }
}