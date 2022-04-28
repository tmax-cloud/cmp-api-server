package com.tmax.cmp.entity.vsphere.vm.vmresources;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
public class BootDevices {

//    @Id
//    @Column(name= "boot_device_id")
//    private long id;

    //enum 테이블 생략
    private String type;

    private String nic;

    private String disks;
}
