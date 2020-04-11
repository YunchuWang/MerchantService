package com.project.passbook.merchantService.security;

public class AccessContext {

  private static final ThreadLocal<String> token = new ThreadLocal<>();

  public static String getToken() {
    return token.get();
  }

  public static void setToken(String tokenVal) {
    token.set(tokenVal);
  }

  public static void clearAccessKey() {
    token.remove();
  }
}
