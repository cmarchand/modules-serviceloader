package top.marchand.demo.java17.modules.professional;

import top.marchand.demo.java17.modules.contract.*;

import java.util.concurrent.TimeUnit;

@Implementation(level = License.LicenseLevel.PROFESSIONAL)
public class ServiceImpl implements Service {
  private License license;
  @Override
  public void freeService() throws InvalidLicenseException {
    throwExceptionIfInvalidLicense();
    System.out.println("<PROFESSIONAL> freeService()");
  };
  @Override
  public void professionalService() throws InvalidLicenseException {
    throwExceptionIfInvalidLicense();
    System.out.println("<PROFESSIONAL> professionalService()");
  };
  @Override
  public void enterpriseService() throws InvalidLicenseException {
    throwExceptionIfInvalidLicense();
    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("<PROFESSIONAL> SLOW enterpriseService()");
  };
  private void throwExceptionIfInvalidLicense() throws InvalidLicenseException {
    if(!LicenseValidator.getInstance().isLicenseValid(license)) throw new InvalidLicenseException();
  }
}
