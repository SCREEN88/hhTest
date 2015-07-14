package hh.price.selenium.utils;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

public enum SeleniumServerInit {
    INITIALIZE;

    private SeleniumServer server = null;

    SeleniumServerInit() {
        RemoteControlConfiguration conf = new RemoteControlConfiguration();
        conf.setPort(DefaultProperties.DATA.getInt("selenium.hubPort"));
        conf.setDebugURL("/wd/hub");
        try {
            server = new SeleniumServer(conf);
        } catch (Exception e) {
            System.out.println("Couldn't start selenium server.");
        }
    }

    public void start() throws Exception {
        server.start();
    }
    public void stop(){
        server.stop();
    }
}
