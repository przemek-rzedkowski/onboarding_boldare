package com.xsolve.data;

public class BuyProductData {

    private Object[][] data = null;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
    private String country;
    private String zone;


    public BuyProductData(Object[][] data) {
        this.data = data;
    }

    public Object[][] getData() {
        return data;
    }
}
