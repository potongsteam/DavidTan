package com.example.individual_assig;

public class billList {
    private String itemName;
    private double itemCost;
    private int quantity;

    public billList(String itemName, double itemCost, int quantity)
    {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.quantity = quantity;

    }

    public static void add(itemBill bill) {
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemNameName(String itemName)
    {
        this.itemName = itemName;
    }
    public double getItemCost()
    {
        return itemCost;
    }

    public void setItemCost(Double itemCost)
    {
        this.itemCost = itemCost;
    }
    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
