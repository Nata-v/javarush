package securityproxy;

import securityproxy.connectors.Connector;
import securityproxy.connectors.SecurityProxyConnector;
import securityproxy.connectors.SimpleConnector;

public class Main {
    public static void main(String[] args) {
        Connector securityProxyConnector = new SecurityProxyConnector("google.com");
        Connector simpleConnector = new SimpleConnector("javarush.ru");

        System.out.println("Connecting with SimpleConnector...");
        simpleConnector.connect();

        System.out.println("--------------------------------------------------");

        System.out.println("Connecting with SecurityProxyConnector...");
        securityProxyConnector.connect();
    }
}
