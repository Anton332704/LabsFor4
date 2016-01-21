package edu.bstu.iipo_15_ivt_1.helppac;

/**
 * Created by user on 29.12.2015.
 */
public class Item_Contact {
    String name;
    String Number;

    public Item_Contact(String name, String number) {
        this.name = name;
        Number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
