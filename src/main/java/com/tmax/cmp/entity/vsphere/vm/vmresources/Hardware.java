package com.tmax.cmp.entity.vsphere.vm.vmresources;

import com.vmware.vapi.bindings.ApiError;
import com.vmware.vcenter.vm.HardwareTypes;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Hardware {

    private String version;
    private String upgrade_policy;
    private HardwareTypes.Version upgrade_version;
    private String upgrade_status;
    private ApiError upgrade_error;

}
