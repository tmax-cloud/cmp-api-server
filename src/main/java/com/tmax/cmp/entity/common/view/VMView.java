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
    private String id;      //uuid
    private String os;      //platform
    private String state;   //state
//    private int cpu;
//    private int memory;

}
