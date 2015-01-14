package com.devicehive.model;

import com.devicehive.json.strategies.JsonPolicyDef;

import static com.devicehive.json.strategies.JsonPolicyDef.Policy.REST_SERVER_CONFIG;

/**
 * Created by tmatvienko on 12/2/14.
 */
public class IdentityProviderConfig implements HiveEntity {

    private static final long serialVersionUID = -2274848199115698341L;

    @JsonPolicyDef(REST_SERVER_CONFIG)
    private String name;

    @JsonPolicyDef(REST_SERVER_CONFIG)
    private String clientId;

    @JsonPolicyDef(REST_SERVER_CONFIG)
    private Long providerId;

    @JsonPolicyDef(REST_SERVER_CONFIG)
    private Boolean isAvailable;

    public IdentityProviderConfig(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
