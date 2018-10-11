package es.source.code.model;

import activity.code.source.es.scos.R;

public class Food {
    private int imageView;
    private String price;
    private String name;
    private boolean order;
    private int store;

    public Food(int imageView,String price,String name,boolean order,int store){
        this.imageView = imageView;
        this.price = price;
        this.name = name;
        this.order = order;
        this.store = store;
    }

    public Food(int imageView,String price,String name){
        this.imageView = imageView;
        this.price = price;
        this.name = name;
    }


    public int getImageView() {
        return imageView;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }
}
