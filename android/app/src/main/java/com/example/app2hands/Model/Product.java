package com.example.app2hands.Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Product implements Parcelable{
    private ArrayList<Uri> img;
    private String productName, productPrice, productStatus, productType, productDescription;

    public Product() {
    }

    public Product(ArrayList<Uri> img, String productName, String productPrice, String productStatus, String productType, String productDescription) {
        this.img = img;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.productType = productType;
        this.productDescription = productDescription;
    }

    protected Product(Parcel in) {
        img = in.createTypedArrayList(Uri.CREATOR);
        productName = in.readString();
        productPrice = in.readString();
        productStatus = in.readString();
        productType = in.readString();
        productDescription = in.readString();
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

    public ArrayList<Uri> getImg() {
        return img;
    }

    public void setImg(ArrayList<Uri> img) {
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(img);
        dest.writeString(productName);
        dest.writeString(productPrice);
        dest.writeString(productStatus);
        dest.writeString(productType);
        dest.writeString(productDescription);
    }
}
