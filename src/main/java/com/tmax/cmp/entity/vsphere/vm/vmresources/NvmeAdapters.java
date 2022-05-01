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
@Table(name="nvme_adapters")
public class NvmeAdapters {

    @Id
    @Column(name = "nvme_adapter_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nvme_adapter_id;

    @ManyToOne
//    @JoinColumn(name = "vm_id")
    @JoinColumn(name = "bios_uuid")
    @JoinColumn(name = "instance_uuid")
    @JoinColumn(name = "identity_name")
    private VsphereVM vm;

    private String label;
    private long bus;
    private long pci_slot_number;
}
