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
@Table(name="serial_ports")
public class SerialPorts {

    @Id
    @Column(name = "serial_port_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serial_port_id;

    @ManyToOne
//    @JoinColumn(name = "vm_id")
    @JoinColumn(name = "bios_uuid")
    @JoinColumn(name = "instance_uuid")
    @JoinColumn(name = "identity_name")
    private VsphereVM vm;

    private boolean allow_guest_control;

    @Embedded
    @AttributeOverride(name = "type", column = @Column(name = "backing_type"))
    @AttributeOverride(name = "file", column = @Column(name = "backing_file"))
    private SerialPortBacking backing;

    private String label;

    private boolean start_connected;

    private String state;

    private boolean yield_on_poll;
}

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
class SerialPortBacking{
    private boolean auto_detect;
    private String file;
    private String host_device;
    private String network_location;
    private boolean no_rx_loss;
    private String pipe;
    private String proxy;
    private String type;

}

