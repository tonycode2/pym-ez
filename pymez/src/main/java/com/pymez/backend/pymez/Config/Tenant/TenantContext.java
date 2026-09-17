package com.pymez.backend.pymez.Config.Tenant;

public class TenantContext {
    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    /**
     * This function will set the Tenant ID into the thread that is handling the
     * request
     * 
     * @param tenantId
     */
    public static void setTenantId(String tenantId) {
        CURRENT_TENANT.set(tenantId);
    }

    /**
     * This function will return the current Tenant ID for the specific thread
     * 
     * @return String
     */
    public static String getTenantId() {
        return CURRENT_TENANT.get();
    }

    /**
     * This function will clear the thread.
     * 
     * @return void
     */
    public static void clear() {
        CURRENT_TENANT.remove();
    }

}
