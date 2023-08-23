package securityproxy.security;

public class SecurityCheckerlmpl implements SecurityChecker{
    @Override
    public boolean performSecurityCheck() {
        System.out.println("SECURITY OK!");
        return true;
    }
}
