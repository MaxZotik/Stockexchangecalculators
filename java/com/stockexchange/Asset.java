package com.stockexchange;

import com.settings.ConstantValues;

import java.io.Serializable;

public abstract class Asset implements Serializable {
    private String name; //название "Акция или валюта"
    private float salePrice; //цена продажи
    private float purchasePrice; //цена покупки
    private int quantity; //количество штук

    public Asset() {
    }

    public Asset(String name, float salePrice, float purchasePrice, int quantity) {
        this.name = name;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public abstract String toString();

    protected static float mathSalePrice(String name, float purchasePrice, int quantity, float keyBroker, float keyMarket){
        float sumBroker = Math.round(((purchasePrice * quantity) / 100 * keyBroker * 2) * 100) / 100;
        float sumMarket = Math.round(((purchasePrice * quantity) / 100 * keyMarket * 2) * 100) / 100;

        return ((purchasePrice * quantity) + sumBroker + sumMarket) / quantity;
    }
}
