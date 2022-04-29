package com.tmax.cmp.entity.vsphere.vm.vmresources;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name= "vsphere_vm")
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class VsphereVM implements Serializable {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "delay", column = @Column(name = "boot_delay")),
            @AttributeOverride(name = "efi_legacy_boot", column = @Column(name = "boot_efi_legacy_boot")),
            @AttributeOverride(name = "enter_setup_mode", column = @Column(name = "boot_enter_setup_mode")),
            @AttributeOverride(name = "network_protocol", column = @Column(name = "boot_network_protocol")),
            @AttributeOverride(name = "retry", column = @Column(name = "boot_retry")),
            @AttributeOverride(name = "retry_delay", column = @Column(name = "boot_retry_delay")),
            @AttributeOverride(name = "type", column = @Column(name = "boot_type"))})
    private Boot boot;

    @EmbeddedId
    private Identity identity;

    private String guest_OS;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "version", column = @Column(name = "hardware_version")),
            @AttributeOverride(name = "upgrade_policy", column = @Column(name = "hardware_upgrade_policy")),
            @AttributeOverride(name = "upgrade_version", column = @Column(name = "hardware_upgrade_version")),
            @AttributeOverride(name = "upgrade_status", column = @Column(name = "hardware_upgrade_status")),
            @AttributeOverride(name = "upgrade_error", column = @Column(name = "hardware_upgrade_error"))})
    private Hardware hardware;

    @OneToMany(mappedBy = "vm", cascade = CascadeType.ALL)
    private Map<String,Ethernet> nics = new HashMap<String,Ethernet>();

    @OneToMany(mappedBy = "vm", cascade = CascadeType.ALL)
    private Map<String,Cdroms> cdroms = new HashMap<String,Cdroms>();

    @OneToMany(mappedBy = "vm", cascade = CascadeType.ALL)
    private Map<String,Disks> disks = new HashMap<String,Disks>();

    @OneToMany(mappedBy = "vm", cascade = CascadeType.ALL)
    private Map<String,Floppies> floppies = new HashMap<String,Floppies>();

    @OneToMany(mappedBy = "vm", cascade = CascadeType.ALL)
    private Map<String,NvmeAdapters> nvme_adapters = new HashMap<String,NvmeAdapters>();

    @OneToMany(mappedBy = "vm", cascade = CascadeType.ALL)
    private Map<String,ParallerPorts> parallel_ports = new HashMap<String,ParallerPorts>();

    @OneToMany(mappedBy = "vm", cascade = CascadeType.ALL)
    private Map<String,SataAdapters> sata_adapters = new HashMap<String,SataAdapters>();

    @OneToMany(mappedBy = "vm", cascade = CascadeType.ALL)
    private Map<String,ScsiAdapters> scsi_adapters = new HashMap<String,ScsiAdapters>();

    @OneToMany(mappedBy = "vm", cascade = CascadeType.ALL)
    private Map<String,SerialPorts> serial_ports = new HashMap<String,SerialPorts>();

    private Boolean instant_clone_frozen;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "size_MiB", column = @Column(name = "memory_size_MiB")),
            @AttributeOverride(name = "hot_add_enabled", column = @Column(name = "memory_hot_add_enabled")),
            @AttributeOverride(name = "hot_add_increment_size_MiB", column = @Column(name = "memory_hot_add_increment_size_MiB")),
            @AttributeOverride(name = "hot_add_limit_MiB", column = @Column(name = "memory_hot_add_limit_MiB"))})
    private Memory memory;

    private String name;

    private String power_state;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "count", column = @Column(name = "cpu_count")),
            @AttributeOverride(name = "cores_per_socket", column = @Column(name = "cpu_cores_per_socket")),
            @AttributeOverride(name = "hot_add_enabled", column = @Column(name = "cpu_hot_add_enabled")),
            @AttributeOverride(name = "hot_remove_enabled", column = @Column(name = "cpu_hot_remove_enabled"))})
    private Cpu cpu;

    @ElementCollection
    @CollectionTable(name = "boot_devices",
            joinColumns = {
//                    @JoinColumn(name = "vm_id"),
                    @JoinColumn(name = "bios_uuid"),
                    @JoinColumn(name = "instance_uuid"),
                    @JoinColumn(name = "identity_name")})
    private List<BootDevices> boot_devices;


}


