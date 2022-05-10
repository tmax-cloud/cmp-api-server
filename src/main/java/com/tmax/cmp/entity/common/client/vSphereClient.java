package com.tmax.cmp.entity.common.client;

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
import lombok.Getter;

import java.util.List;

public class vSphereClient implements Client{

    private StubFactory stubFactory;
    private StubConfiguration stubConfig;
    private static final String HTTPS = "https://";
    private static final String VAPI_PATH = "/api";
    @Getter
    private VM vmClient;
    @Getter
    private Session sessionClient;

    public vSphereClient(String server, String username, String password) {
        HttpConfiguration httpConfig = new HttpConfiguration.Builder()
                .setSslConfiguration(buildSslConfiguration())
                .getConfig();
        this.stubFactory = createApiStubFactory(server,httpConfig);

        ExecutionContext.SecurityContext securityContext = SecurityContextFactory.createUserPassSecurityContext(username, password.toCharArray());
        this.stubConfig = new StubConfiguration(securityContext);
        this.sessionConnect();
    }

    public StubFactory createApiStubFactory(String server, HttpConfiguration httpConfig) {
        String apiUrl = HTTPS + server + VAPI_PATH;
        ProtocolConnection connection = new ProtocolFactory().getHttpConnection(apiUrl, null, httpConfig);
        StubFactory stubFactory = new StubFactory(connection.getApiProvider());
        return stubFactory;
    }

    protected HttpConfiguration.SslConfiguration buildSslConfiguration() {
        HttpConfiguration.SslConfiguration sslConfig;

        SslUtil.trustAllHttpsCertificates();
        sslConfig = new HttpConfiguration.SslConfiguration.Builder()
                .disableCertificateValidation()
                .disableHostnameVerification()
                .getConfig();

        return sslConfig;
    }

    public void sessionConnect(){
        Session session = this.stubFactory.createStub(Session.class,this.stubConfig);

        char[] sessionId = session.create();
        SessionSecurityContext sessionSecurityContext = new SessionSecurityContext(sessionId);
        this.stubConfig.setSecurityContext(sessionSecurityContext);

        this.sessionClient = this.stubFactory.createStub(Session.class,this.stubConfig);
        this.vmClient = this.stubFactory.createStub(VM.class,this.stubConfig);
    }
}
