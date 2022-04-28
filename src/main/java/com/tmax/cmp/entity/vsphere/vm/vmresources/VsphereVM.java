package com.tmax.cmp.entity.vsphere.vm.vmresources;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "vsphere_vm")
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VsphereVM implements Serializable {

    @Id
    @Column(name= "vm_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

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
//    @AttributeOverride(name = "name", column = @Column(name = "identity_name"))
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


//    @ElementCollection
//    @CollectionTable(name = "boot_devices",
//            joinColumns = {
//                    @JoinColumn(name = "vm_id"),
//                    @JoinColumn(name = "bios_uuid"),
//                    @JoinColumn(name = "instance_uuid"),
//                    @JoinColumn(name = "identity_name")})
//    private List<BootDevices> boot_devices;


}


