package hu.bme.android.todorecyclerview.data;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Todo extends SugarRecord implements Serializable {
    private String name;
    private String price;
    private boolean bought;
    private String type;
    private String details;

    public Todo() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Todo(String name, String price, boolean bought, String type, String details) {
        this.name = name;
        this.price=price;
        this.bought = bought;
        this.type=type;
        this.details=details;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public void setPrice(String price){ this.price=price;}
    public String getPrice(){ return price;}
}
