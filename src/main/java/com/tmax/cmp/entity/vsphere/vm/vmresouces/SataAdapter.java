package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vcenter.vm.hardware.adapter.SataTypes;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class SataAdapter {

    private String sata_label;
    private SataTypes.Type sata_type;
    private long sata_bus;
    private long sata_pciSlotNumber;
}
