package com.project.passbook.merchantService.security;

import com.project.passbook.merchantService.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String token = request.getHeader(Constants.TOKEN_NAME);

    if (StringUtils.isBlank(token)) {
      throw new Exception("Header missing " + Constants.TOKEN_NAME + "!");
    }
    // TODO: Add real jwt token generation/auth later, now just use the same token to validate all users
    if (!token.equals(Constants.TOKEN)) {
      throw new Exception("Invalid auth token!");
    }
    AccessContext.setToken(token);

    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {}

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    AccessContext.clearAccessKey();
  }
}
