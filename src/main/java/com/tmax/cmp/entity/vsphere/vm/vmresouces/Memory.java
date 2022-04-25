package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Memory {

    private long memory_sizeMiB;
    private boolean memory_hotAddEnabled;
    private Long memory_hotAddIncrementSizeMiB;
    private Long memory_hotAddLimitMiB;
}
