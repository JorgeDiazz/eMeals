package com.jorgediaz.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Lifecycle;

import com.google.android.material.textfield.TextInputEditText;
import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.databinding.FragmentRecipeDetailsBinding;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class RecipeDetailsFragment extends DialogFragment implements MenuProvider {

    private FragmentRecipeDetailsBinding binding;
    private RecipeUiModel recipeUiModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeProperties();
        initializeView();
    }

    private void initializeProperties() {
        recipeUiModel = RecipeDetailsFragmentArgs.fromBundle(requireArguments()).getRecipeUiModel();
    }

    private void initializeView() {
        initializeToolbar();
    }

    private void initializeToolbar() {
        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.mainToolbar.getToolbar());
        binding.mainToolbar.styleToolbarWithLightTheme();
        binding.mainToolbar.setTitle(recipeUiModel.getTitle());
        binding.mainToolbar.setSubtitle(recipeUiModel.getStyle());


        binding.mainToolbar.setToolbarMenu(R.menu.menu_recipe_details_toolbar);
        binding.mainToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_edit_title) {
                onEditTitleActionPressed();
                return true;
            }
            return false;
        });

        binding.mainToolbar.setBackButton(true);
        binding.mainToolbar.setBackButtonListener(this::popFragment);
    }

    private void onEditTitleActionPressed() {
        showEditTitleAlertDialog();
    }

    private void showEditTitleAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(getString(R.string.edit_recipe_title));

        View viewInflated = getLayoutInflater().inflate(R.layout.alert_dialog_input_text, (ViewGroup) getView(), false);
        final TextInputEditText input = (TextInputEditText) viewInflated.findViewById(R.id.editText_content);
        input.setText(recipeUiModel.getTitle());
        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            dialog.dismiss();
            //m_Text = input.getText().toString();
        });

        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void popFragment() {
        requireActivity().onBackPressed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_recipe_details_toolbar, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}