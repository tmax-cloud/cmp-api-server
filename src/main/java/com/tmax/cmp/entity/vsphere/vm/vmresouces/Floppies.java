package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vcenter.vm.hardware.ConnectionState;
import com.vmware.vcenter.vm.hardware.FloppyTypes;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Floppies {

    private String floppies_label;
    private FloppyTypes.BackingInfo floppies_backing;
    private ConnectionState floppies_state;
    private boolean floppies_startConnected;
    private boolean floppies_allowGuestControl;
}
