package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Identity {

    private String identity_name;
    private String identity_biosUuid;
    private String identity_instanceUuid;

}
