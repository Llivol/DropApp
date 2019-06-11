package com.example.dropapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dropapp.Models.Item;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "details",
        "id",
        "points_spent"
})
public class ComandaData {

    @JsonProperty("details")
    private List<ItemData> details = null;
    @JsonProperty("id")
    private String id;
    @JsonProperty("points_spent")
    private String pointsSpent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("details")
    public List<ItemData> getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(List<ItemData> details) {
        this.details = details;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("points_spent")
    public String getPointsSpent() {
        return pointsSpent;
    }

    @JsonProperty("points_spent")
    public void setPointsSpent(String pointsSpent) {
        this.pointsSpent = pointsSpent;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String itemsToString() {

        String itemsToString = "";

        // TODO: Passar això al fitxer d'strings
        if (details.size() == 0) return "No s'ha demanat res!";

        for (ItemData item :
                this.details) {

            if (item.getAmount() > 0) {

                itemsToString += (String.format("%s (x%s), ", item.getItem(), Long.toString(item.getAmount())));
            }

            if (item.getAmountD() > 0) {

                itemsToString +=(String.format("%s desc. (x%s), ", item.getItem(), Long.toString(item.getAmountD())));
            }
        }

        if ( itemsToString.length() != 0 ) itemsToString = itemsToString.substring(0, itemsToString.length() - 2);

        return itemsToString;
    }

}