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
@Table(name="cdroms")
public class Cdroms {

    @Id
    @Column(name = "cdrom_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cdrom_Id;

    @ManyToOne
//    @JoinColumn(name = "vm_id")
    @JoinColumn(name = "bios_uuid")
    @JoinColumn(name = "instance_uuid")
    @JoinColumn(name = "identity_name")
    private VsphereVM vm;

    private boolean allow_guest_control;

    @Embedded
    @AttributeOverride(name = "type", column = @Column(name = "backing_type"))
    private CdromBacking backing;

    @Embedded
    private Ide ide;

    private String label;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "bus", column = @Column(name = "sata_bus")),
            @AttributeOverride(name = "unit", column = @Column(name = "sata_unit"))})
    private Sata sata;

    private boolean start_connected;

    private String state;

    private String type;

}

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
class CdromBacking {
    private boolean auto_detect;
    private String device_access_type;
    private String host_device;
    private String iso_file;
    private String type;
}