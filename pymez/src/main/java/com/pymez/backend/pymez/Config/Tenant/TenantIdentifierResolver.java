package com.pymez.backend.pymez.Config.Tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getTenantId();

        return tenantId != null ? tenantId : "PUBLIC";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}
