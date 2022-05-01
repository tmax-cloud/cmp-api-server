package com.tmax.cmp.svc.vspheretest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmax.cmp.entity.vsphere.vm.vmresources.Disks;

import javax.persistence.AttributeConverter;

public class DiskJsonConverter implements AttributeConverter<Disks, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Disks disk) {

        try{
            return objectMapper.writeValueAsString(disk);
        } catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public Disks convertToEntityAttribute(String jsonString){

        try{
            return objectMapper.readValue(jsonString, Disks.class);
        }catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
