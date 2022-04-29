package com.tmax.cmp.entity.vsphere.vm.vmresources;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Identity implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vm_id;

    private String bios_uuid;
    @Column(name = "identity_name")
    private String name;
    private String instance_uuid;

}
