package com.stockexchange;

import com.settings.ConstantValues;

import java.io.Serializable;

public class Stock extends Asset implements Serializable {

    public Stock() {
    }

    public Stock(String name, float purchasePrice, int quantity, float keyBroker,float keyMarket) {
        super(name,
                mathSalePrice(purchasePrice, quantity, keyBroker, keyMarket),
                purchasePrice,
                quantity);
    }

    @Override
    public String toString() {
        return getName() + "\n" + "Цена продажи " + getSalePrice();
    }
}
