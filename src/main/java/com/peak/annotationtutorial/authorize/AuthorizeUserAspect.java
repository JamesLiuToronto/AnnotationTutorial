package com.peak.annotationtutorial.authorize;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Conditional(AuthorizeCondition.class)
public class AuthorizeUserAspect {

    static String AUTH_ERROR = "AUTHORIZE.INVALID";
    @Around("@annotation(AuthorizeUser)")
    public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs() ;
        //BEFORE METHOD EXECUTION
        String token = (String) getParameterByName(joinPoint, "access_token");
        validateToken(token) ;

        //This is where ACTUAL METHOD will get invoke
        Object result = joinPoint.proceed();

        // AFTER METHOD EXECUTION
        //System.out.println(result);
        return result;
    }

    public Object getParameterByName(ProceedingJoinPoint proceedingJoinPoint, String parameterName) {
        MethodSignature methodSig = (MethodSignature) proceedingJoinPoint.getSignature();
        Object[] args = proceedingJoinPoint.getArgs();
        String[] parametersName = methodSig.getParameterNames();

        int idx = Arrays.asList(parametersName).indexOf(parameterName);

        if(args.length > idx) { // parameter exist
            return args[idx];
        } // otherwise your parameter does not exist by given name
        return null;

    }

    private void validateToken(String token){
        if ((token == null)||(token.equalsIgnoreCase("deny")))
            throw new AuthorizeException(AUTH_ERROR) ;
    }
}
