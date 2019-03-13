package com.dhcc.ms.ims.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
  private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
      'f' };

  public static String generateSHA(String value) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-1");
      byte[] bytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));
      return new String(encodeHex(bytes, 0, 8));
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e);
    }
  }

  private static char[] encodeHex(byte[] bytes, int offset, int length) {
    char chars[] = new char[length];
    for (int i = 0; i < length; i = i + 2) {
      byte b = bytes[offset + (i / 2)];
      chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
      chars[i + 1] = HEX_CHARS[b & 0xf];
    }
    return chars;
  }
}
