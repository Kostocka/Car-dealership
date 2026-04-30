package peipo.ru.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component("roleChecker")
public class RoleChecker
{
    public boolean hasAnyRole(Authentication authentication, String[] roles)
    {
        if (authentication == null || !authentication.isAuthenticated())
        {
            return false;
        }

        for (GrantedAuthority grantedAuthority : authentication.getAuthorities())
        {
            for (String role : roles)
            {
                if (grantedAuthority.getAuthority().equals("ROLE_" + role))
                {
                    return true;
                }
            }
        }


        return false;
    }
}