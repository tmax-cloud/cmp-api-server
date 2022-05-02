package com.tmax.cmp.svc.vsphere;

import com.vmware.vapi.bindings.StubConfigurationBase;
import com.vmware.vapi.bindings.client.RetryPolicy;
import com.vmware.vapi.bindings.type.ErrorType;
import com.vmware.vapi.core.ExecutionContext;
import com.vmware.vapi.internal.util.Validate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VsphereStubConfiguration {

    public final class StubConfiguration extends StubConfigurationBase {
        private ExecutionContext.SecurityContext securityContext;
        private Set<ErrorType> registeredErrorTypes = new HashSet();
        private RetryPolicy retryPolicy = null;

        public StubConfiguration() {
        }

        public StubConfiguration(ExecutionContext.SecurityContext securityContext) {
            this.securityContext = securityContext;
        }

        public void setSecurityContext(ExecutionContext.SecurityContext context) {
            this.securityContext = context;
        }

        public ExecutionContext.SecurityContext getSecurityContext() {
            return this.securityContext;
        }

        public void registerErrorTypes(List<ErrorType> errors) {
            Validate.noNullElements(errors);
            this.registeredErrorTypes.addAll(errors);
        }

        public Set<ErrorType> getErrorTypes() {
            return this.registeredErrorTypes;
        }

        public void setRetryPolicy(RetryPolicy retryPolicy) {
            this.retryPolicy = retryPolicy;
        }

        public RetryPolicy getRetryPolicy() {
            return this.retryPolicy;
        }
    }
}
