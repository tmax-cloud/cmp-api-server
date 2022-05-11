package com.tmax.cmp.entity.vsphere.vm.vmresources;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
@Setter
@EqualsAndHashCode
public class Identity implements Serializable {

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(nullable = true)
//    private Long vm_id;

    private String bios_uuid;
    @Column(name = "identity_name")
    private String name;
    private String instance_uuid;

}
