
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
        "active",
        "connected"
})

/*
    Classe que permet guardar la informació dels slots en format Json
 */
public class SlotsData {

    @JsonProperty("active")
    private Long active;
    @JsonProperty("connected")
    private Long connected;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("active")
    public Long getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Long active) {
        this.active = active;
    }

    public SlotsData withActive(Long active) {
        this.active = active;
        return this;
    }

    @JsonProperty("connected")
    public Long getConnected() {
        return connected;
    }

    @JsonProperty("connected")
    public void setConnected(Long connected) {
        this.connected = connected;
    }

    public SlotsData withConnected(Long connected) {
        this.connected = connected;
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

    public SlotsData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
