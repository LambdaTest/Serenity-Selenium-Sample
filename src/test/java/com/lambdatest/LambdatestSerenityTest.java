package com.lambdatest;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.lambdatest.tunnel.Tunnel;

public class LambdatestSerenityTest {
    private static Tunnel ltTunnel;

    public static void checkAndStartLambdaTestTunnel(DesiredCapabilities capabilities,String user, String accessKey)
            throws Exception {
        if (ltTunnel != null) {
            return;
        }
        if (capabilities.getCapability("LT:Options") != null
                && ((JSONObject) capabilities.getCapability("LT:Options")).get("tunnel") != null
                && ((boolean) ((JSONObject) capabilities.getCapability("LT:Options")).get("tunnel")) == true) {
            ltTunnel = new Tunnel();
            Map<String, String> options = new HashMap<String, String>();
            options.put("user", user);
            options.put("key", accessKey);
            ltTunnel.start(options);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (ltTunnel != null) {
            ltTunnel.stop();
        }
    }
}
