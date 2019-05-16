package com.example.dropapp.Models;

import com.example.dropapp.R;

import java.util.List;

public class Order {

    private int id;
    private String pointsSpent;
    private List<Item> details;

    public Order(int id, String pointsSpent, List<Item> details) {
        this.id = id;
        this.pointsSpent = pointsSpent;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPointsSpent() {
        return pointsSpent;
    }

    public void setPointsSpent(String pointsSpent) {
        this.pointsSpent = pointsSpent;
    }

    public List<Item> getItems() {
        return details;
    }

    public void setItems(List<Item> details) {
        this.details = details;
    }

    public String itemsToString() {

        String itemsToString = "";

        // TODO: Passar això al fitxer d'strings
        if (details.size() == 0) return "No s'ha demanat res!";

        for (Item item :
                this.details) {

            if (item.getAmount() > 0) {

                itemsToString += (String.format("%s (x%s), ", item.getItem(), Long.toString(item.getAmount())));
            }

            if (item.getAmountD() > 0) {

                itemsToString +=(String.format("%s DROP (x%s), ", item.getItem(), Long.toString(item.getAmountD())));
            }
        }

        if ( itemsToString.length() != 0 ) itemsToString = itemsToString.substring(0, itemsToString.length() - 2);

        return itemsToString;
    }
}

// TODO: Passar classe a JSON

/* A l'antiga versió era:

package com.example.dropadria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ComandaJsonData {

    @JsonProperty("details")
    private List<ItemAmountJsonData> details = null;
    @JsonProperty("id")
    private int id;
    @JsonProperty("points_spent")
    private String pointsSpent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("details")
    public List<ItemAmountJsonData> getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(List<ItemAmountJsonData> details) {
        this.details = details;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
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

}

 */
