package com.zmfx.csc.audit;

import com.zmfx.csc.domain.enumeration.EntityAuditAction;

@FunctionalInterface
public interface EntityAuditEventWriter {
    public void writeAuditEvent(Object target, EntityAuditAction action);
}
