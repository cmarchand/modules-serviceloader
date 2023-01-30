package top.marchand.demo.java17.modules.contract;

import top.marchand.demo.java17.modules.contract.validator.ShadedLicenseValidator;

public class LicenseValidator {
  private static LicenseValidator instance;
  private ShadedLicenseValidator shaded;
  public static LicenseValidator getInstance() {
    if(instance==null) instance = new LicenseValidator();
    return instance;
  }
  private LicenseValidator() {}
  public boolean isLicenseValid(License license) {
    return shaded.isLicenseValid(license);
  }
}
