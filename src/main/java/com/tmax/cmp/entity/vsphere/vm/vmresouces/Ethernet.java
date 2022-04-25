package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vcenter.vm.hardware.ConnectionState;
import com.vmware.vcenter.vm.hardware.EthernetTypes;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Ethernet {

    private String ethernet_label;
    private EthernetTypes.EmulationType ethernet_type;
    private Boolean ethernet_uptCompatibilityEnabled;
    private EthernetTypes.MacAddressType ethernet_macType;
    private String ethernet_macAddress;
    private Long ethernet_pciSlotNumber;
    private boolean ethernet_wakeOnLanEnabled;
    private EthernetTypes.BackingInfo ethernet_backing;
    private ConnectionState ethernet_state;
    private boolean ethernet_startConnected;
    private boolean ethernet_allowGuestControl;
}
