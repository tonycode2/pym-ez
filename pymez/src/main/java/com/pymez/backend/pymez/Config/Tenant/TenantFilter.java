package com.pymez.backend.pymez.Config.Tenant;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantFilter extends OncePerRequestFilter {

    private static final String TENANT_HEADER = "X-Tenant-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tenantId = request.getHeader(TENANT_HEADER);

        try {
            if (tenantId != null && !tenantId.isBlank()) {
                TenantContext.setTenantId(tenantId);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing X-Tenant-ID header");
                return;
            }

            filterChain.doFilter(request, response);

        } finally {
            TenantContext.clear();
        }
    }

}
