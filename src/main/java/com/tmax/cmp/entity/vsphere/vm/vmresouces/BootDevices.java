package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vcenter.vm.hardware.boot.DeviceTypes;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Embeddable
@Table(name= "boot_devices")
public class BootDevices {

    @Id
    @Column(name= "BOOT_DEVICE_ID")
    private long id;

    //enum 테이블 생략
    private DeviceTypes.Type type;

    private String nic;

    //Embeddable 내부에 ElementCollection 사용 불가 (List타입 string으로 변환해서 사용해야할듯)
//    @ElementCollection
    private String disks;
}
