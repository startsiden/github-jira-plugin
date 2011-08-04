package com.abctech.jira.github;

/**
 * Created by IntelliJ IDEA.
 * User: andremar
 * Date: 8/4/11
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;
import java.io.*;
import java.security.*;
public class MD5Util {
  public static String hex(byte[] array) {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
	  sb.append(Integer.toHexString((array[i]
	      & 0xFF) | 0x100).substring(1,3));
      }
      return sb.toString();
  }
  public static String md5Hex (String message) {
      try {
	  MessageDigest md =
	      MessageDigest.getInstance("MD5");
	  return hex (md.digest(message.getBytes("CP1252")));
      } catch (NoSuchAlgorithmException e) {
      } catch (UnsupportedEncodingException e) {
      }
      return null;
  }
}
