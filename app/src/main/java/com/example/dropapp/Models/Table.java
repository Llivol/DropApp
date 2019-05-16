package com.example.dropapp.Models;

public class Table {

    private int id;
    private long score;
    private String status;

    public Table(int id, long score, String status) {
        this.id = id;
        this.score = score;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


// TODO: Passar classe a JSON

/* A l'antiga versió era:


package com.example.dropadria;

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

public class TableJsonData {

    @JsonProperty("id")
    private String id;
    @JsonProperty("points")
    private Long points;
    @JsonProperty("slots")
    private SlotsJsonData slots;
    @JsonProperty("status")
    private String status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonCreator
    public TableJsonData(@JsonProperty("id") String id,
                         @JsonProperty("points") Long points,
                         @JsonProperty("slots") SlotsJsonData slots,
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

    public TableJsonData withId(String id) {
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

    public TableJsonData withPoints(Long points) {
        this.points = points;
        return this;
    }

    @JsonProperty("slots")
    public SlotsJsonData getSlots() {
        return slots;
    }

    @JsonProperty("slots")
    public void setSlots(SlotsJsonData slots) {
        this.slots = slots;
    }

    public TableJsonData withSlots(SlotsJsonData slots) {
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

    public TableJsonData withStatus(String status) {
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

    public TableJsonData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}


 */