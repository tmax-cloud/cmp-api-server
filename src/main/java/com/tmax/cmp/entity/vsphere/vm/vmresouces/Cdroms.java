package com.tmax.cmp.entity.vsphere.vm.vmresouces;

import com.vmware.vcenter.vm.hardware.CdromTypes;
import com.vmware.vcenter.vm.hardware.ConnectionState;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Cdroms {

    private boolean cdrom_allowGuestControl;
    private CdromTypes.BackingInfo cdrom_backing;

    //하위테이블 통합
    private long cdrom_sataBus;
    private long cdrom_sataUnit;
    private boolean cdrom_idePrimary;
    private boolean cdrom_ideMaster;

    private String cdrom_label;

    //enum type 생략
    private boolean cdrom_startConnected;
    private ConnectionState cdrom_state;

    //enum type 생략
    private CdromTypes.HostBusAdapterType cdrom_type;

}
