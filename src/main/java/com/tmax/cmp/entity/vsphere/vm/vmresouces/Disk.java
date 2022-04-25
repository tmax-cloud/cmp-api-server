package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vcenter.vm.hardware.DiskTypes;
import com.vmware.vcenter.vm.hardware.IdeAddressInfo;
import com.vmware.vcenter.vm.hardware.SataAddressInfo;
import com.vmware.vcenter.vm.hardware.ScsiAddressInfo;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Disk {

    private String disk_label;
    private DiskTypes.HostBusAdapterType disk_type;
    private IdeAddressInfo disk_ide;
    private ScsiAddressInfo disk_scsi;
    private SataAddressInfo disk_sata;
    private DiskTypes.BackingInfo disk_backing;
    private Long disk_capacity;

}
