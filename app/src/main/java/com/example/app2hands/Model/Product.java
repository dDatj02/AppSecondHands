package com.example.app2hands.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int img;
    private String productName, productPrice, productStatus;

    public Product() {}

    public Product(int img, String productName, String productPrice, String productStatus) {
        this.img = img;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
    }

    protected Product(Parcel in) {
        img = in.readInt();
        productName = in.readString();
        productPrice = in.readString();
        productStatus = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(img);
        dest.writeString(productName);
        dest.writeString(productPrice);
        dest.writeString(productStatus);
    }
}
