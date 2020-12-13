
package pl.michal.Shop.model.views;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "userId",
    "creationTime",
    "total sum",
    "orderlineViews"
})
public class OrderResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("creationTime")
    private String creationTime;

    @JsonProperty("orderlineViews")
    private List<OrderlineView> orderlineViews = new ArrayList<OrderlineView>();
    @JsonProperty("total sum")
    private Double totalSum;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("creationTime")
    public String getCreationTime() {
        return creationTime;
    }

    @JsonProperty("creationTime")
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    @JsonProperty("total sum")
    public Double getTotalSum() {
        return totalSum;
    }

    @JsonProperty("total sum")
    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    @JsonProperty("orderlineViews")
    public List<OrderlineView> getOrderlineViews() {
        return orderlineViews;
    }

    @JsonProperty("orderlineViews")
    public void setOrderlineViews(List<OrderlineView> orderlineViews) {
        this.orderlineViews = orderlineViews;
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
        StringBuilder sb = new StringBuilder();
        sb.append(OrderResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("creationTime");
        sb.append('=');
        sb.append(((this.creationTime == null)?"<null>":this.creationTime));
        sb.append(',');
        sb.append("totalSum");
        sb.append('=');
        sb.append(((this.totalSum == null)?"<null>":this.totalSum));
        sb.append(',');
        sb.append("orderlineViews");
        sb.append('=');
        sb.append(((this.orderlineViews == null)?"<null>":this.orderlineViews));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.totalSum == null)? 0 :this.totalSum.hashCode()));
        result = ((result* 31)+((this.creationTime == null)? 0 :this.creationTime.hashCode()));
        result = ((result* 31)+((this.orderlineViews == null)? 0 :this.orderlineViews.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.userId == null)? 0 :this.userId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderResponse) == false) {
            return false;
        }
        OrderResponse rhs = ((OrderResponse) other);
        return (((((((this.totalSum == rhs.totalSum)||((this.totalSum!= null)&&this.totalSum.equals(rhs.totalSum)))&&((this.creationTime == rhs.creationTime)||((this.creationTime!= null)&&this.creationTime.equals(rhs.creationTime))))&&((this.orderlineViews == rhs.orderlineViews)||((this.orderlineViews!= null)&&this.orderlineViews.equals(rhs.orderlineViews))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.userId == rhs.userId)||((this.userId!= null)&&this.userId.equals(rhs.userId))));
    }

}
