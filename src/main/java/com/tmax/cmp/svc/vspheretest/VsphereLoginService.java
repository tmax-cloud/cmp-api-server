//package com.tmax.cmp.svc.auth;
//
//import com.vmware.vapi.protocol.HttpConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class VsphereLoginService {
//
//    @Autowired
//    VsphereAuthHelper vapiAuthHelper;
//
//    @Autowired
//    VsphereStubConfiguration sessionStubConfiguration;
//
//    public void login(String server, String userName, String passoword) throws Exception {
//
//        this.vapiAuthHelper = new VsphereAuthHelper();
//        HttpConfiguration httpConfig = buildHttpConfiguration();
//        this.sessionStubConfiguration = vapiAuthHelper
//                .loginByUsernameAndPassword(server,userName,passoword)
//
//    }
//
//    protected HttpConfiguration buildHttpConfiguration() throws Exception {
//        HttpConfiguration httpConfig =
//                new HttpConfiguration.Builder()
//                        .setSslConfiguration(buildSslConfiguration())
//                        .getConfig();
//
//        return httpConfig;
//    }
//}
