/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.san;

/**
 *
 * @author M1028704
 */
public class BarcodeType {

    public static enum TYPES {

        Code128,
        Code128A,
        Code128B,
        Code128C,
        UCC128,
        EAN128,
        USPS,
        ShipmentIdentificationNumber,
        SSCC18,
        SCC14ShippingCode,
        GlobalTradeItemNumber,
        EAN13,
        Bookland,
        UPCA,
        RandomWeightUPCA,
        Std2of5,
        Int2of5,
        PDF417,
        Code39,
        //        _3of9,
        USD3,
        Codabar,
        USD4,
        NW7,
        Monarch,
        //        _2of7,
        PostNet
    };

    public static String[] getNameList() {
//        return new String[]{"Code128", "Code128A", "Code128B", "Code128C", "UCC128", "EAN128", "USPS", "ShipmentIdentificationNumber", "SSCC18", "SCC14ShippingCode", "GlobalTradeItemNumber", "EAN13", "Bookland", "UPCA", "RandomWeightUPCA", "Std2of5", "Std2of5", "Int2of5", "Int2of5", "PDF417", "Code39", "3of9", "USD3", "Codabar", "USD4", "NW7", "Monarch", "2of7", "PostNet"};
        return new String[]{"Code128", "Code128A", "Code128B", "Code128C", "UCC128", "EAN128", "USPS", "ShipmentIdentificationNumber", "SSCC18", "SCC14ShippingCode", "GlobalTradeItemNumber", "EAN13", "Bookland", "UPCA", "RandomWeightUPCA", "Std2of5", "Std2of5", "Int2of5", "Int2of5", "PDF417", "Code39", "USD3", "Codabar", "USD4", "NW7", "Monarch", "PostNet"};
    }

}
