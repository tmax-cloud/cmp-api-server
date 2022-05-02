package com.tmax.cmp.entity.common.view;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@ToString
@Table(name = "vm_view")
public class VMView {

    @Id
    private String id;
    private String os;
    private String state;

}
