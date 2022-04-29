package com.tmax.cmp.entity.vsphere.vm.vmresources;

import com.vmware.vcenter.vm.hardware.BootTypes;
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
public class Boot {

    private long delay;
    private Boolean efi_legacy_boot;
    private boolean enter_setup_mode;
    private BootTypes.NetworkProtocol network_protocol;
    private boolean retry;
    private long retry_delay;
    private BootTypes.Type type;

}
