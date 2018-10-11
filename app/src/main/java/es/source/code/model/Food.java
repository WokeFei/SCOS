package es.source.code.model;

import activity.code.source.es.scos.R;

public class Food {
    private int imageView;
    private String price;
    private String name;
    private String btn_order;

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

    public String getBtn_order() {
        return btn_order;
    }

    public void setBtn_order(String btn_order) {
        this.btn_order = btn_order;
    }
}
