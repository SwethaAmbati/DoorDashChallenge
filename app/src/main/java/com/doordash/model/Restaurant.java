package com.doordash.model;

/**
 * Created by SwethaAmbati on 5/24/17.
 */

public class Restaurant {
    private String name;
    private int id;
    private String description;
    private String cover_img_url;
    private String status;
    private float delivery_fee;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover_img_url() {
        return cover_img_url;
    }

    public void setCover_img_url(String cover_img_url) {
        this.cover_img_url = cover_img_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(float delivery_fee) {
        this.delivery_fee = delivery_fee;
    }



}
