package com.tmax.cmp.svc.vsphere;

import com.vmware.authorization.utils.SslUtil;
import com.vmware.cis.Session;
import com.vmware.vapi.bindings.StubConfiguration;
import com.vmware.vapi.bindings.StubFactory;
import com.vmware.vapi.cis.authn.ProtocolFactory;
import com.vmware.vapi.cis.authn.SecurityContextFactory;
import com.vmware.vapi.core.ApiProvider;
import com.vmware.vapi.core.ExecutionContext;
import com.vmware.vapi.protocol.HttpConfiguration;
import com.vmware.vapi.protocol.ProtocolConnection;
import com.vmware.vapi.security.SessionSecurityContext;
import com.vmware.vcenter.VM;
import com.vmware.vcenter.VMTypes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUtils {


    private Session sessionSvc;
    private StubFactory stubFactory;
    public static final String VAPI_PATH = "/api";

    public StubFactory createApiStubFactory(String server,
                                            HttpConfiguration httpConfig)
            throws Exception {
        // Create a https connection with the vapi url
        ProtocolFactory pf = new ProtocolFactory();
        String apiUrl = "https://" + server + VAPI_PATH;

        // Get a connection to the vapi url
        ProtocolConnection connection =
                pf.getHttpConnection(apiUrl, null, httpConfig);

        // Initialize the stub factory with the api provider
        ApiProvider provider = connection.getApiProvider();
        StubFactory stubFactory = new StubFactory(provider);
        return stubFactory;
    }

    protected HttpConfiguration buildHttpConfiguration() throws Exception {
        HttpConfiguration httpConfig =
                new HttpConfiguration.Builder()
                        .setSslConfiguration(buildSslConfiguration())
                        .getConfig();

        return httpConfig;
    }

    protected HttpConfiguration.SslConfiguration buildSslConfiguration() throws Exception {
        HttpConfiguration.SslConfiguration sslConfig;

        SslUtil.trustAllHttpsCertificates();
        sslConfig = new HttpConfiguration.SslConfiguration.Builder()
                    .disableCertificateValidation()
                    .disableHostnameVerification()
                    .getConfig();

        return sslConfig;
    }

    public void loginByUsernameAndPassword(
        String server, String username, String password) throws Exception {

        HttpConfiguration httpConfig = buildHttpConfiguration();

        this.stubFactory = createApiStubFactory(server,httpConfig);

        ExecutionContext.SecurityContext securityContext =
                SecurityContextFactory.createUserPassSecurityContext(
                        username,password.toCharArray());

        StubConfiguration stubConfig = new StubConfiguration(securityContext);

        //stubFactory로  session 구성
        Session session = this.stubFactory.createStub(Session.class,stubConfig);

        char[] sessionId = session.create();

        SessionSecurityContext sessionSecurityContext = new SessionSecurityContext(sessionId);

        stubConfig.setSecurityContext(sessionSecurityContext);

        //sessionSvc 구성
        this.sessionSvc = this.stubFactory.createStub(Session.class,stubConfig);

        //vmService(Client) 구성
        VM vmService = this.stubFactory.createStub(VM.class,stubConfig);
        List<VMTypes.Summary> vmList = vmService.list(new VMTypes.FilterSpec());

        for(VMTypes.Summary vmSummary : vmList){
            System.out.println(vmSummary);
        }
    }

}
