package top.marchand.demo.java17.modules.contract.validator;

import top.marchand.demo.java17.modules.contract.License;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ShadedLicenseValidator {
  private static ShadedLicenseValidator instance;
  private static final String MARKER = "37a3e462-0f50-4aa7-be3a-1309225ca647";
  public static ShadedLicenseValidator getInstance() {
    if(instance==null) instance = new ShadedLicenseValidator();
    return instance;
  }
  public boolean isLicenseValid(License license) {
    String checksum = calculateChecksum(license);
    return checksum.equals(license.checksum());
  }

  String calculateChecksum(License license) {
    String value = license.licensee() + "/" + license.level() + "/" + MARKER;
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.update(value.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(digest.digest());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
