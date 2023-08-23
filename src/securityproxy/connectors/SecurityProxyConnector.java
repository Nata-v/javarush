package securityproxy.connectors;

import securityproxy.security.SecurityChecker;
import securityproxy.security.SecurityCheckerlmpl;

public class SecurityProxyConnector implements Connector{
    SimpleConnector simpleConnector;
    SecurityChecker securityChecker = new SecurityCheckerlmpl();
// проверка безопасности перед подключением
    @Override
    public void connect() {
        System.out.println("Performing security check...");
        if (securityChecker.performSecurityCheck()){
            simpleConnector.connect();
        }else {
            System.out.println("FAILED SECURITY CHECK, WON'T CONNECT!");
        }
    }
    public SecurityProxyConnector(String resourceString){
        this.simpleConnector = new SimpleConnector(resourceString);
    }
}
