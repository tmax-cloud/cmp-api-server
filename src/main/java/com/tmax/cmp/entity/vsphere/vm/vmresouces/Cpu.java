package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Cpu {

    private long cpu_count;
    private long cpu_coresPerSocket;
    private boolean cpu_hotAddEnabled;
    private boolean cpu_hotRemoveEnabled;

}
