package com.microsoft.bing.ecommerce.samples;

class MyProduct {
    public String ProductId;
    public String ProductTitle;
    public String ProductDescription;
    public Double ProductPrice;
    public String arbitraryText;
    public Double arbitraryNumber;

    public MyProduct(String id, String title, String desc, Double price, String text, Double number) {
        ProductId = id;
        ProductTitle = title;
        ProductDescription = desc;
        ProductPrice = price;
        arbitraryText = text;
        arbitraryNumber = number;
    }
}