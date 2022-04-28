package com.tmax.cmp.repository;

import com.tmax.cmp.entity.vsphere.vm.vmresources.VsphereVM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VsphereVMRepository extends JpaRepository<VsphereVM, Long> {
}
