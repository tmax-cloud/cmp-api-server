package com.tmax.cmp.entity.vsphere.vm.vmresources;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="sata_adapters")
public class SataAdapters {

    @Id
    @Column(name = "sata_adapter_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sata_adapter_id;

    @ManyToOne
//    @JoinColumn(name = "vm_id")
    @JoinColumn(name = "bios_uuid")
    @JoinColumn(name = "instance_uuid")
    @JoinColumn(name = "identity_name")
    private VsphereVM vm;

    private String label;
    private String type;
    private long bus;
    private long pci_slot_number;
}
