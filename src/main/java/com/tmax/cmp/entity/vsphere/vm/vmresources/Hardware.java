package com.tmax.cmp.entity.vsphere.vm.vmresources;

import com.vmware.vapi.bindings.ApiError;
import com.vmware.vcenter.vm.HardwareTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Hardware {

    private HardwareTypes.Version version;
    private HardwareTypes.UpgradePolicy upgrade_policy;
    private HardwareTypes.Version upgrade_version;
    private HardwareTypes.UpgradeStatus upgrade_status;
    private ApiError upgrade_error;

}
