package com.jorgediaz.presentation.ui.adapters;

import static com.jorgediaz.presentation.ui.FormatUtils.ONE_DECIMAL_FORMAT_PATTERN;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.databinding.CardItemRecipeBinding;
import com.jorgediaz.presentation.ui.model.RecipeUiModel;

import java.util.List;
import java.util.Locale;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private final List<RecipeUiModel> recipeUiModelList;
    private final OnRecipeClickListener onRecipeClickListener;
    private final Context context;

    public RecipesAdapter(List<RecipeUiModel> recipeUiModelList, OnRecipeClickListener onRecipeClickListener, Context context) {
        this.recipeUiModelList = recipeUiModelList;
        this.onRecipeClickListener = onRecipeClickListener;
        this.context = context;
    }

    public interface OnRecipeClickListener {
        void onRecipeClicked(RecipeUiModel recipeUiModel);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item_recipe, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bind(recipeUiModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return recipeUiModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CardItemRecipeBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = CardItemRecipeBinding.bind(view);
        }

        void bind(RecipeUiModel recipeUiModel) {
            final ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
            progressBarDrawable.setColor(context.getColor(R.color.green_sushi));
            progressBarDrawable.setBackgroundColor(context.getColor(R.color.orange_outrageous));
            progressBarDrawable.setRadius(context.getResources().getDimensionPixelSize(R.dimen.radius_background_small_200));
            binding.imageViewRecipe.getHierarchy().setProgressBarImage(progressBarDrawable);

            binding.imageViewRecipe.setImageURI(recipeUiModel.getPrimaryPictureUrlMedium());
            binding.textViewRecipeServings.setText(String.valueOf(recipeUiModel.getServings()));
            binding.textViewRecipeTime.setText(String.valueOf(recipeUiModel.getTime()));
            binding.textViewRecipeRating.setText(String.format(Locale.ENGLISH, ONE_DECIMAL_FORMAT_PATTERN, recipeUiModel.getRating()));
            binding.textViewRecipeTitle.setText(recipeUiModel.getTitle());
            binding.textViewRecipeStyle.setText(recipeUiModel.getStyle());

            binding.cardItemRecipe.setOnClickListener(view -> onRecipeClickListener.onRecipeClicked(recipeUiModel));
        }

    }
}
