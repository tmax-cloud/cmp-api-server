package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vapi.bindings.ApiError;
import com.vmware.vcenter.vm.HardwareTypes;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Hardware {

    private HardwareTypes.Version hardware_version;
    private HardwareTypes.UpgradePolicy hardware_upgradePolicy;
    private HardwareTypes.Version hardware_upgradeVersion;
    private HardwareTypes.UpgradeStatus hardware_upgradeStatus;
    private ApiError hardware_upgradeError;

}
