package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vcenter.vm.hardware.ConnectionState;
import com.vmware.vcenter.vm.hardware.ParallelTypes;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class ParallerPort {

    private String parrallel_port_label;
    private ParallelTypes.BackingInfo parrallel_port_backing;
    private ConnectionState parrallel_port_state;
    private boolean parrallel_port_startConnected;
    private boolean parrallel_port_allowGuestControl;
}
