package com.example.dropapp.Models;

public class Item {

    private long amount;
    private long amountD;
    private float discPrice;
    private String item;
    private int pointCost;
    private float price;

    public Item(String item, long amount, long amountD, float price, float discPrice, int pointCost) {
        this.amount = amount;
        this.amountD = amountD;
        this.discPrice = discPrice;
        this.item = item;
        this.pointCost = pointCost;
        this.price = price;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmountD() {
        return amountD;
    }

    public void setAmountD(long amountD) {
        this.amountD = amountD;
    }

    public float getDiscPrice() {
        return discPrice;
    }

    public void setDiscPrice(float discPrice) {
        this.discPrice = discPrice;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPointCost() {
        return pointCost;
    }

    public void setPointCost(int pointCost) {
        this.pointCost = pointCost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getAcummulatedAmount() {
        return this.amount + this.amountD;
    }
}

// TODO: Passar aquesta classe a JSON

/* A l'antiga versió era:


package com.example.dropadria;

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

public class ItemJsonData {

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

    public ItemJsonData withAmount(Long amount) {
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

    public ItemJsonData withAmountD(Long amountD) {
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

    public ItemJsonData withDiscPrice(float discPrice) {
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

    public ItemJsonData withItem(String item) {
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

    public ItemJsonData withPointCost(int pointCost) {
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

    public ItemJsonData withPrice(float price) {
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

    public ItemJsonData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public Long getAcummulatedAmount() {
        return this.amount + this.amountD;
    }

}


 */
