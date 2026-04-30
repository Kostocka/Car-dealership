package peipo.ru.common.security;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RolesAllowedAspect
{
    private final RoleChecker roleChecker;

    public RolesAllowedAspect(RoleChecker roleChecker)
    {
        this.roleChecker = roleChecker;
    }

    @Before("@annotation(rolesAllowed)")
    public void checkRole(JoinPoint joinPoint, RolesAllowed rolesAllowed)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!roleChecker.hasAnyRole(authentication, rolesAllowed.value()))
        {
            throw new AccessDeniedException("You don't have enough rights");
        }
    }
}