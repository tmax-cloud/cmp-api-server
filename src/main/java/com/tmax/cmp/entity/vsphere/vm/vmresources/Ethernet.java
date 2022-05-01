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
@Table(name="ethernet")
public class Ethernet {

    @Id
    @Column(name = "ethernet_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
//    @JoinColumn(name = "vm_id")
    @JoinColumn(name = "bios_uuid")
    @JoinColumn(name = "instance_uuid")
    @JoinColumn(name = "identity_name")
    private VsphereVM vm;

    private boolean allow_guest_control;

    @Embedded
    @AttributeOverride(name = "type", column = @Column(name = "backing_type"))
    private Ethernet_Backing backing;

    private String label;

    private String mac_address;
    private String mac_type;
    private int pci_slot_number;
    private boolean start_connected;
    private String state;
    private String type;
    private boolean upt_compatibility_enabled;
    private boolean wake_on_lan_enabled;
}

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
class Ethernet_Backing{
    private int connection_cookie;
    private String distributed_port;
    private String distributed_switch_uuid;
    private String host_device;
    private String network;
    private String network_name;
    private String opaque_network_id;
    private String opaque_network_type;
    private String type;
}
