package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vcenter.vm.hardware.BootTypes;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Boot {

    private BootTypes.Type boot_type;
    private Boolean boot_efiLegacyBoot;
    private BootTypes.NetworkProtocol boot_networkProtocol;
    private long boot_delay;
    private boolean boot_retry;
    private long boot_retryDelay;
    private boolean boot_enterSetupMode;

}
