package com.peak.annotationtutorial.authorize;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizeUserAspect {

    @Around("@annotation(AuthorizeUser)")
    public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {

        //BEFORE METHOD EXECUTION
        Integer user_id = (Integer) joinPoint.getArgs()[0];
        System.out.println("User Id: " + user_id);

        //Only user id 33 is authorize to login, other user are not valid users.
        if (user_id != 33) {
            //write authorization verification business logic
            //System.out.println("Invalid User : " + user_id);
            //return user_id + " is Invalid User. Please login with correct credential.";
            throw new AuthorizeException("AUTHORIZE.INVALID") ;
        }

        //This is where ACTUAL METHOD will get invoke
        Object result = joinPoint.proceed();

        // AFTER METHOD EXECUTION
        System.out.println(result);
        return result;
    }
}
