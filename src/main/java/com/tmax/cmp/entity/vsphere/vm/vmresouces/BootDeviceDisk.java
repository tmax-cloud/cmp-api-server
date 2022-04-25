package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class BootDeviceDisk {

    @Id
    String disk;
}
