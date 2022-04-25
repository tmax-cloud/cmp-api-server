package com.tmax.cmp.entity.vsphere.vm;

import com.tmax.cmp.entity.vsphere.vm.vmresouces.*;
import com.vmware.vcenter.vm.GuestOS;
import com.vmware.vcenter.vm.PowerTypes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "vsphere_vm")
public class VsphereVM {

    @Id
    @Column(name= "VM_ID")
    private Long id;

    @Embedded
    private Identity identity;

    private static final long serialVersionUID = 1L;

    private GuestOS guestOS;

    private String name;

    private PowerTypes.State powerState;

    private Boolean instantCloneFrozen;

    @Embedded
    private Hardware hardware;

    @Embedded
    private Boot boot;

    @ElementCollection
    @CollectionTable(name= "boot_devices", joinColumns = @JoinColumn(name= "VM_ID"))
    private List<BootDevices> bootDevices = new ArrayList<BootDevices>();

    @Embedded
    private Cpu cpu;

    @Embedded
    private Memory memory;

    @Embedded
    private Disk disks;

    @Embedded
    private Ethernet nics;

    @Embedded
    private Cdroms cdroms;

    @Embedded
    private Floppies floppies;

    @Embedded
    private ParallerPort parallelPorts;

    @Embedded
    private SerialPort serialPorts;

    @Embedded
    private SataAdapter sataAdapters;

    @Embedded
    private ScsiAdapter scsiAdapters;

}

