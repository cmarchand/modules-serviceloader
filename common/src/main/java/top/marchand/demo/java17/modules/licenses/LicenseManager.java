package top.marchand.demo.java17.modules.licenses;

import top.marchand.demo.java17.modules.UnparsableLicenseException;
import top.marchand.demo.java17.modules.contract.License;

public class LicenseManager {
  private static LicenseManager instance;

  private LicenseManager() {

  }
  public static LicenseManager getInstance() {
    if(instance==null) {
      instance = new LicenseManager();
    }
    return instance;
  }
  public License getLicense() throws UnparsableLicenseException {
    return LicenseLoader.loadLicense();
  }
}
