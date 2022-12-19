package com.jorgediaz.presentation;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jorgediaz.presentation.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Represents main activity.
 *
 * This is the orchestrator of app's views.
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}