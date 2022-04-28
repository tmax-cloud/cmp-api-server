package com.tmax.cmp.entity.vsphere.vm.vmresources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="disks")
public class Disks {

    @Id
    @Column(name = "disk_id")
    private Long disk_id;

//    @ManyToOne
//    @JoinColumn(name = "vm_id")
//    @JoinColumn(name = "bios_uuid")
//    @JoinColumn(name = "instance_uuid")
//    @JoinColumn(name = "identity_name")
//    private VsphereVM vm;

    @Embedded
    @AttributeOverride(name = "type", column = @Column(name = "backing_type"))
    private Disk_Backing backing;

    @Embedded
    private Ide ide;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "bus", column = @Column(name = "nvme_bus")),
            @AttributeOverride(name = "unit", column = @Column(name = "nvme_unit"))})
    private Nvme nvme;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "bus", column = @Column(name = "sata_bus")),
            @AttributeOverride(name = "unit", column = @Column(name = "sata_unit"))})
    private Sata sata;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "bus", column = @Column(name = "scsi_bus")),
            @AttributeOverride(name = "unit", column = @Column(name = "scsi_unit"))})
    private Scsi scsi;

    private Long capacity;

    private String label;

    private String type;

}

@Embeddable
class Disk_Backing {
    private String type;
    private String vmdk_file;
}

@Embeddable
class Ide {
    @Column(name = "ide_primary")
    private boolean primary;
    @Column(name = "ide_master")
    private boolean master;
}

@Embeddable
class Nvme {
    private int bus;
    private int unit;
}

@Embeddable
class Sata {
    private int bus;
    private int unit;
}

@Embeddable
class Scsi {
    private int bus;
    private int unit;
}
