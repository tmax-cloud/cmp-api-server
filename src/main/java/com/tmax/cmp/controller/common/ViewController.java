package com.tmax.cmp.controller.common;

import com.tmax.cmp.entity.common.view.VMView;
import com.tmax.cmp.svc.common.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/view")
public class ViewController {

    @Autowired
    ViewService viewService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Map<Integer,VMView>> viewAllVMs(){

        HashMap<Integer,VMView> response = viewService.getAllInstancesAndVms();

        System.out.println(response.get(1));
        return new ResponseEntity<Map<Integer,VMView>>(response, HttpStatus.OK);
    }

}
