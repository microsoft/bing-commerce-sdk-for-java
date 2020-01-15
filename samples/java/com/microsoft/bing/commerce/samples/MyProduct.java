// Copyright (c) Microsoft Corporation.
// Licensed under the MIT license.

package com.microsoft.bing.commerce.samples;

class MyProduct {
    public String ProductId;
    public String ProductTitle;
    public String ProductDescription;
    public Double ProductPrice;
    public String ProductDetailsUrl;
    public String ArbitraryText;
    public Double ArbitraryNumber;

    public MyProduct(String id, String title, String desc, Double price, String url, String text, Double number) {
        ProductId = id;
        ProductTitle = title;
        ProductDescription = desc;
        ProductPrice = price;
        ProductDescription = url;
        ArbitraryText = text;
        ArbitraryNumber = number;
    }
}
