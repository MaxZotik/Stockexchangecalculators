package com.stockexchange;

import java.io.Serializable;

public class Currency extends Asset implements Serializable {

    public Currency() {
    }

    public Currency(String name, float purchasePrice, int quantity, float keyBroker, float keyMarket) {
        super(name,
                mathSalePrice(name, purchasePrice, quantity, keyBroker, keyMarket),
                purchasePrice,
                quantity);
    }

    @Override
    public String toString() {
        return getName() + "/n" + "Цена продажы " + getSalePrice();
    }
}
