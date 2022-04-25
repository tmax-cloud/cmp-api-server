package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vcenter.vm.hardware.ConnectionState;
import com.vmware.vcenter.vm.hardware.SerialTypes;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class SerialPort {


    private String serialport_label;
    private boolean serialport_yieldOnPoll;
    private SerialTypes.BackingInfo serialport_backing;
    private ConnectionState serialport_state;
    private boolean serialport_startConnected;
    private boolean serialport_allowGuestControl;
}
