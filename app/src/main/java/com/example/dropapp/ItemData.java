
package com.example.dropapp;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "amount",
        "amount_d",
        "disc_price",
        "item",
        "point_cost",
        "price"
})

/*
    Classe que permet guardar la informació dels items en format Json
 */
public class ItemData {

    @JsonProperty("amount")
    private Long amount;
    @JsonProperty("amount_d")
    private Long amountD;
    @JsonProperty("disc_price")
    private float discPrice;
    @JsonProperty("item")
    private String item;
    @JsonProperty("point_cost")
    private int pointCost;
    @JsonProperty("price")
    private float price;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("amount")
    public Long getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ItemData withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    @JsonProperty("amount_d")
    public Long getAmountD() {
        return amountD;
    }

    @JsonProperty("amount_d")
    public void setAmountD(Long amountD) {
        this.amountD = amountD;
    }

    public ItemData withAmountD(Long amountD) {
        this.amountD = amountD;
        return this;
    }

    @JsonProperty("disc_price")
    public float getDiscPrice() {
        return discPrice;
    }

    @JsonProperty("disc_price")
    public void setDiscPrice(float discPrice) {
        this.discPrice = discPrice;
    }

    public ItemData withDiscPrice(float discPrice) {
        this.discPrice = discPrice;
        return this;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    public ItemData withItem(String item) {
        this.item = item;
        return this;
    }

    @JsonProperty("point_cost")
    public int getPointCost() {
        return pointCost;
    }

    @JsonProperty("point_cost")
    public void setPointCost(int pointCost) {
        this.pointCost = pointCost;
    }

    public ItemData withPointCost(int pointCost) {
        this.pointCost = pointCost;
        return this;
    }

    @JsonProperty("price")
    public float getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(float price) {
        this.price = price;
    }

    public ItemData withPrice(float price) {
        this.price = price;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ItemData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public Long getAcummulatedAmount() {
        return this.amount + this.amountD;
    }

}
