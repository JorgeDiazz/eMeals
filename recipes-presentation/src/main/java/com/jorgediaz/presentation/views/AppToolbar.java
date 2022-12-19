package com.jorgediaz.presentation.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.databinding.AppToolbarBinding;

public class AppToolbar extends LinearLayout {

    private final Toolbar toolbar;
    private final int padding;

    public AppToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);

        AppToolbarBinding binding = AppToolbarBinding.inflate(LayoutInflater.from(context), this, true);
        toolbar = binding.toolbarCustom;

        setBackgroundResource(R.color.orange_outrageous);

        padding = getResources().getDimensionPixelSize(R.dimen.spacing_normal_600);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.AppToolbar);
        boolean showBackButton = attributes.getBoolean(R.styleable.AppToolbar_showBackButton, false);
        setBackButton(showBackButton);

        styleToolbarWithLightTheme();

        attributes.recycle();
    }

    private void setPaddingForToolbar() {
        toolbar.setPadding(padding, 0, padding, 0);
    }

    public void setTitle(String title) {
        setPaddingForToolbar();

        toolbar.setTitle(title == null ? "" : title);
    }

    public void styleToolbarWithLightTheme() {
        setPaddingForToolbar();
        setBackgroundResource(R.color.orange_outrageous);

        toolbar.setTitleTextAppearance(getContext(), R.style.AppToolbarTitleTextAppearance_Dark);
        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setTint(ContextCompat.getColor(getContext(), R.color.white));
        }
        invalidate();
        requestLayout();
    }

    public void setBackButton(boolean show) {
        setPaddingForToolbar();
        toolbar.setNavigationIcon(
                show ? ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_back) : null);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setTint(ContextCompat.getColor(getContext(), R.color.white));
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}