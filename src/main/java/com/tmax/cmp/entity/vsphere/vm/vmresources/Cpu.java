package com.tmax.cmp.entity.vsphere.vm.vmresources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Cpu {

    private long count;
    private long cores_per_socket;
    private boolean hot_add_enabled;
    private boolean hot_remove_enabled;

}
