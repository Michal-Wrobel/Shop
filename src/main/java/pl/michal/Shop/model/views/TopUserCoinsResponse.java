//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.michal.Shop.model.views;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id", "userId", "total sum"})
public class TopUserCoinsResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("total sum")
    private Double totalCoins;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap();

    public TopUserCoinsResponse() {
    }

    @JsonProperty("id")
    public String getId() {
        return this.id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return this.lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("total sum")
    public Double getTotalCoins() {
        return this.totalCoins;
    }

    @JsonProperty("total sum")
    public void setTotalCoins(Double totalCoins) {
        this.totalCoins = totalCoins;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "TopUserCoinsResponse{}";
    }

//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(TopUserCoins.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
//        sb.append("id");
//        sb.append('=');
//        sb.append(this.id == null ? "<null>" : this.id);
//        sb.append(',');
//        sb.append("name");
//        sb.append('=');
//        sb.append(this.name == null ? "<null>" : this.name);
//        sb.append(',');
//        sb.append("lastName");
//        sb.append('=');
//        sb.append(this.lastName == null ? "<null>" : this.lastName);
//        sb.append(',');
//        sb.append("totalSum");
//        sb.append('=');
//        sb.append(this.totalSum == null ? "<null>" : this.totalSum);
//        sb.append(',');
//        sb.append("additionalProperties");
//        sb.append('=');
//        sb.append(this.additionalProperties == null ? "<null>" : this.additionalProperties);
//        sb.append(',');
//        if (sb.charAt(sb.length() - 1) == ',') {
//            sb.setCharAt(sb.length() - 1, ']');
//        } else {
//            sb.append(']');
//        }
//
//        return sb.toString();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopUserCoinsResponse that = (TopUserCoinsResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(totalCoins, that.totalCoins) &&
                Objects.equals(additionalProperties, that.additionalProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, totalCoins, additionalProperties);
    }
}
