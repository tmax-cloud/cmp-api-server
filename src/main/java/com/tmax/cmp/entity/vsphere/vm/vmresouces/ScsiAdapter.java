package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vcenter.vm.hardware.adapter.ScsiTypes;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class ScsiAdapter {

    private String scsi_label;
    private ScsiTypes.Type scsi_type;
    private Long scsi_long;
    private Long scsi_unit;
    private Long scsi_pciSlotNumber;
    private ScsiTypes.Sharing scsi_sharing;
}
