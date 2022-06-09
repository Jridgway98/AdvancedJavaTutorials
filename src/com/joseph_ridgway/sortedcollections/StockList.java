package com.joseph_ridgway.sortedcollections;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new TreeMap<>();
    }

    public int addStock(StockItem item) {
        if(item != null) {
            // Check if already have quantities of this item.
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // If there are already stocks of this item, adjust the quantity
            if(inStock != item) {
                item.adjustStock(inStock.quantityInStock());
            }

            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        if((inStock != null) && (inStock.quantityInStock() >= quantity) && (quantity > 0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, StockItem> items() {
        return Collections.unmodifiableMap(list);
    }

    public Map<String, Double> priceList() {
        Map<String, Double> prices = new TreeMap<>();
        for(Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for(Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();
            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s+stockItem + ". There are " + stockItem.quantityInStock() + " in stock. Value of items: ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }

        return s + "Total stock value " + totalCost;
    }
}
