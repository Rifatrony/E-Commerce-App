package com.rony.e_commerceapp.Response;

import java.util.ArrayList;

public class ProductDetailsResponse {

    public String id;
    public String name;
    public String slug;
    public String description;
    public String sku;
    public String price;
    public String discount;
    public String discount_type;
    public double final_price;
    public String thumbnail;
    public String image1;
    public String image2;
    public String image3;
    public Category category;
    public Brand brand;
    public ArrayList<Tag> tag;
    public boolean hasAttribute;
    public ArrayList<Object> attributes;

    public ProductDetailsResponse(String id, String name, String slug, String description, String sku, String price, String discount, String discount_type, double final_price, String thumbnail, String image1, String image2, String image3, Category category, Brand brand, ArrayList<Tag> tag, boolean hasAttribute, ArrayList<Object> attributes) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.discount = discount;
        this.discount_type = discount_type;
        this.final_price = final_price;
        this.thumbnail = thumbnail;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.category = category;
        this.brand = brand;
        this.tag = tag;
        this.hasAttribute = hasAttribute;
        this.attributes = attributes;
    }

    public ProductDetailsResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(double final_price) {
        this.final_price = final_price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ArrayList<Tag> getTag() {
        return tag;
    }

    public void setTag(ArrayList<Tag> tag) {
        this.tag = tag;
    }

    public boolean isHasAttribute() {
        return hasAttribute;
    }

    public void setHasAttribute(boolean hasAttribute) {
        this.hasAttribute = hasAttribute;
    }

    public ArrayList<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Object> attributes) {
        this.attributes = attributes;
    }

    public class Brand{
        public String name;
        public String slug;
        public String image;
        public Object url;
    }

    public class Category{
        public String name;
        public String slug;
        public String icon;
        public String image;
        public Object description;
    }

    public class Tag{
        public String name;
        public String slug;
    }

}
