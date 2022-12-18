package com.jorgediaz.presentation.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.MenuRes;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.jorgediaz.presentation.R;
import com.jorgediaz.presentation.databinding.AppToolbarBinding;

public class AppToolbar extends LinearLayout {

    private final Toolbar toolbar;
    private int toolbarMenuId = -1;
    private final int padding;

    public AppToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);

        AppToolbarBinding binding = AppToolbarBinding.inflate(LayoutInflater.from(context), this, true);
        toolbar = binding.toolbarCustom;

        setBackgroundResource(R.color.white);

        padding = getResources().getDimensionPixelSize(R.dimen.spacing_normal_600);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.AppToolbar);
        boolean showBackButton = attributes.getBoolean(R.styleable.AppToolbar_showBackButton, false);
        setBackButton(showBackButton);

        styleToolbarWithLightTheme();
        supportMultipleLinesTitle();

        attributes.recycle();
    }

    private void setPaddingForToolbar() {
        if (toolbarMenuId != -1 && toolbar.getNavigationIcon() != null) {
            toolbar.setPadding(0, 0, 0, 0);
        } else if (toolbar.getNavigationIcon() != null) {
            toolbar.setPadding(0, 0, padding, 0);
        } else if (toolbarMenuId != -1) {
            toolbar.setPadding(padding, 0, 0, 0);
        } else {
            toolbar.setPadding(padding, 0, padding, 0);
        }
    }

    public void setToolbarMenu(@MenuRes int menuId) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(menuId);
        toolbarMenuId = menuId;

        setPaddingForToolbar();
    }

    public void setTitle(String title) {
        setPaddingForToolbar();
        supportMultipleLinesTitle();

        toolbar.setTitle(title == null ? "" : title);
    }

    private void supportMultipleLinesTitle() {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View child = toolbar.getChildAt(i);

            if (child instanceof TextView) {
                ((TextView) child).setSingleLine(false);
            }
        }
    }

    public void setSubtitle(String subtitle) {
        toolbar.setSubtitle(subtitle == null ? "" : subtitle);
    }

    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener listener) {
        toolbar.setOnMenuItemClickListener(listener);
    }

    public void styleToolbarWithLightTheme() {
        setPaddingForToolbar();
        setBackgroundResource(R.color.white);

        toolbar.setTitleTextAppearance(getContext(), R.style.AppToolbarTitleTextAppearance_Dark);
        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setTint(ContextCompat.getColor(getContext(), R.color.blue_dark));
        }
        invalidate();
        requestLayout();
    }

    public void setBackButton(boolean show) {
        setPaddingForToolbar();
        toolbar.setNavigationIcon(
                show ? ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_back) : null);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setTint(ContextCompat.getColor(getContext(), R.color.blue_dark));
        }
    }

    public void setBackButtonListener(Runnable listener) {
        toolbar.setNavigationOnClickListener(
                v -> {
                    listener.run();
                });
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public int getToolbarMenuId() {
        return toolbarMenuId;
    }

    public int getPadding() {
        return padding;
    }
}