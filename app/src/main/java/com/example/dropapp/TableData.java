
package com.example.dropapp;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "points",
        "slots",
        "status"
})
/*
    Classe que permet guardar la informació dels les taules en format Json
 */
public class TableData {

    @JsonProperty("id")
    private String id;
    @JsonProperty("points")
    private Long points;
    @JsonProperty("slots")
    private SlotsData slots;
    @JsonProperty("status")
    private String status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonCreator
    public TableData(@JsonProperty("id") String id,
                     @JsonProperty("points") Long points,
                     @JsonProperty("slots") SlotsData slots,
                     @JsonProperty("status") String status) {

        this.id = id;
        this.points = points;
        this.slots = slots;
        this.status = status;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public TableData withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("points")
    public Long getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(Long points) {
        this.points = points;
    }

    public TableData withPoints(Long points) {
        this.points = points;
        return this;
    }

    @JsonProperty("slots")
    public SlotsData getSlots() {
        return slots;
    }

    @JsonProperty("slots")
    public void setSlots(SlotsData slots) {
        this.slots = slots;
    }

    public TableData withSlots(SlotsData slots) {
        this.slots = slots;
        return this;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public TableData withStatus(String status) {
        this.status = status;
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

    public TableData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
