package com.tmax.cmp.entity.vsphere.vm.vmresources;

import com.vmware.vapi.bindings.ApiError;
import com.vmware.vcenter.vm.HardwareTypes;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Hardware {

    private HardwareTypes.Version version;
    private HardwareTypes.UpgradePolicy upgrade_policy;
    private HardwareTypes.Version upgrade_version;
    private HardwareTypes.UpgradeStatus upgrade_status;
    private ApiError upgrade_error;

}
