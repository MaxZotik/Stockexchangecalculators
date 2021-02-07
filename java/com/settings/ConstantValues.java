package com.settings;

import java.util.HashMap;

final public class ConstantValues {
    private static final HashMap<String, Float> valuesCommission = new HashMap();

    public static Float get(String key){
        return valuesCommission.get(key);
    }

    public static void init(){
        valuesCommission.put("STOCK_BROKER", 0.05f); //коммиссия банка акции
        valuesCommission.put("STOCK_MARKET", 0.01f); //коммиссия биржи акции
        valuesCommission.put("CURRENCY_BROKER", 0.05f); //коммиссия банка валюта
        valuesCommission.put("CURRENCY_MARKET", 1.0f); //коммиссия биржи валюта
    }
}
