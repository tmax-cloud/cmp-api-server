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
@Table(name="floppies")
public class Floppies {

    @Id
    @Column(name = "floppies_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long floppies_id;

    @ManyToOne
//    @JoinColumn(name = "vm_id")
    @JoinColumn(name = "bios_uuid")
    @JoinColumn(name = "instance_uuid")
    @JoinColumn(name = "identity_name")
    private VsphereVM vm;

    private boolean allow_guest_control;

    @Embedded
    @AttributeOverride(name = "type", column = @Column(name = "backing_type"))
    private Floppy_Backing backing;

    private String label;

    private boolean start_connected;

    private String state;
}

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
class Floppy_Backing{
    private boolean auto_detect;
    private String host_device;
    private String image_file;
    private String type;
}
