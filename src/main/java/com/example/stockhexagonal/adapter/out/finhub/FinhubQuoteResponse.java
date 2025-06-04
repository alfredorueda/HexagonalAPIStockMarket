package com.example.stockhexagonal.adapter.out.finhub;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data transfer object for Finhub API quote response
 */
public class FinhubQuoteResponse {
    
    @JsonProperty("c")
    private double currentPrice;
    
    @JsonProperty("h")
    private double highPrice;
    
    @JsonProperty("l")
    private double lowPrice;
    
    @JsonProperty("o")
    private double openPrice;
    
    @JsonProperty("pc")
    private double previousClosePrice;
    
    // Default constructor for Jackson
    public FinhubQuoteResponse() {
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getPreviousClosePrice() {
        return previousClosePrice;
    }

    public void setPreviousClosePrice(double previousClosePrice) {
        this.previousClosePrice = previousClosePrice;
    }
}
