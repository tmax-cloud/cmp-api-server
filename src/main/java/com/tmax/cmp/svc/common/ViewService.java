package com.tmax.cmp.svc.common;

import com.tmax.cmp.entity.common.view.VMView;
import com.tmax.cmp.repository.VMViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ViewService {

    @Autowired
    VMViewRepository vmViewRepository;

    public HashMap<Integer,VMView> getAllInstancesAndVms(){

        List<VMView> viewList = vmViewRepository.findAll();
        HashMap<Integer,VMView> vmMap = new HashMap<>();

        int num = 1;
        for(VMView vmView : viewList){
            vmMap.put(num,vmView);
            num++;
        }

        return vmMap;
    }
}
