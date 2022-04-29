package com.tmax.cmp.entity.vsphere.vm.vmresources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.util.ArrayList;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BootDevices {

//    @Id
//    @Column(name= "boot_device_id")
//    private long id;

    //enum 테이블 생략
    private String type;

    private String nic;

    private ArrayList<String> disks = new ArrayList<>();
}
