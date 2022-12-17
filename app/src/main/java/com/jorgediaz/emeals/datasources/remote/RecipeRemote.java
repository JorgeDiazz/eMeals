package com.jorgediaz.emeals.datasources.remote;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

import java.util.List;

@JsonClass(generateAdapter = true, generator = "java")
public class RecipeRemote {

    @Json(name = "id")
    private int id;

    @Json(name = "items")
    private List<ItemRemote> items;

    @Json(name = "main")
    private MainRemote main;

    @Json(name = "plan_mobile_title")
    private String planMobileTitle;

    @Json(name = "plan_size")
    private String planSize;

    @Json(name = "plan_style")
    private String planStyle;

    @Json(name = "plan_style_id")
    private int planStyleId;

    @Json(name = "plan_title")
    private String planTitle;

    @Json(name = "plan_title_without_size")
    private String planTitleWithoutSize;

    @Json(name = "side")
    private SideRemote side;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemRemote> getItems() {
        return items;
    }

    public void setItems(List<ItemRemote> items) {
        this.items = items;
    }

    public MainRemote getMain() {
        return main;
    }

    public void setMain(MainRemote main) {
        this.main = main;
    }

    public String getPlanMobileTitle() {
        return planMobileTitle;
    }

    public void setPlanMobileTitle(String planMobileTitle) {
        this.planMobileTitle = planMobileTitle;
    }

    public String getPlanSize() {
        return planSize;
    }

    public void setPlanSize(String planSize) {
        this.planSize = planSize;
    }

    public String getPlanStyle() {
        return planStyle;
    }

    public void setPlanStyle(String planStyle) {
        this.planStyle = planStyle;
    }

    public int getPlanStyleId() {
        return planStyleId;
    }

    public void setPlanStyleId(int planStyleId) {
        this.planStyleId = planStyleId;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String getPlanTitleWithoutSize() {
        return planTitleWithoutSize;
    }

    public void setPlanTitleWithoutSize(String planTitleWithoutSize) {
        this.planTitleWithoutSize = planTitleWithoutSize;
    }

    public SideRemote getSide() {
        return side;
    }

    public void setSide(SideRemote side) {
        this.side = side;
    }
}
