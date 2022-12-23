package com.example.app2hands.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    @SerializedName("_id")
    private String id;
    private String image, name, price, status, type, description, sellerAvt, sellerName;


    protected Product(Parcel in) {
        id = in.readString();
        image = in.readString();
        name = in.readString();
        price = in.readString();
        status = in.readString();
        type = in.readString();
        description = in.readString();
        sellerAvt = in.readString();
        sellerName = in.readString();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerAvt() {
        return sellerAvt;
    }

    public void setSellerAvt(String sellerAvt) {
        this.sellerAvt = sellerAvt;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(image);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(status);
        parcel.writeString(type);
        parcel.writeString(description);
        parcel.writeString(sellerAvt);
        parcel.writeString(sellerName);
    }
}
