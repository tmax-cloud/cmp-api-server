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
@Table(name="scsi_adapters")
public class ScsiAdapters {

    @Id
    @Column(name = "scsi_adapter_id")
    private Long scsi_adapter_id;

    @ManyToOne
    @JoinColumn(name = "vm_id")
    @JoinColumn(name = "bios_uuid")
    @JoinColumn(name = "instance_uuid")
    @JoinColumn(name = "identity_name")
    private VsphereVM vm;

    private String label;

    private Long pci_slot_number;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "bus", column = @Column(name = "scsi_bus")),
            @AttributeOverride(name = "unit", column = @Column(name = "scsi_unit"))})
    private Scsi scsi;

    private String sharing;

    private String type;

}
