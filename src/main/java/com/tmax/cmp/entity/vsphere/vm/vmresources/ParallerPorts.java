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
@Table(name="parallel_ports")
public class ParallerPorts {

    @Id
    @Column(name = "parallel_port_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vm_id")
    @JoinColumn(name = "bios_uuid")
    @JoinColumn(name = "instance_uuid")
    @JoinColumn(name = "identity_name")
    private VsphereVM vm;

    private boolean allow_guest_control;

    @Embedded
    @AttributeOverride(name = "type", column = @Column(name = "backing_type"))
    @AttributeOverride(name = "file", column = @Column(name = "backing_file"))
    private Parallel_Port_Backing backing;

    private String label;

    private boolean start_connected;

    private String state;
}

@Embeddable
class Parallel_Port_Backing{
    private boolean auto_detect;
    private String file;
    private String host_device;
    private String type;
}

