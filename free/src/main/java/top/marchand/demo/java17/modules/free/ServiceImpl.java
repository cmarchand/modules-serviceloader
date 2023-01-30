package top.marchand.demo.java17.modules.free;

import top.marchand.demo.java17.modules.contract.*;

@Implementation(level = License.LicenseLevel.FREE)
public class ServiceImpl implements Service {
  private License license;

  @Override
  public void freeService() throws InvalidLicenseException {
    throwExceptionIfInvalidLicense();
    System.out.println("<FREE> freeService()");
  }

  @Override
  public void professionalService() throws InvalidLicenseException {
    throwExceptionIfInvalidLicense();
    System.out.println("<FREE> ILLEGAL professionalService()");
  };

  @Override
  public void enterpriseService() throws InvalidLicenseException {
    throwExceptionIfInvalidLicense();
    System.out.println("<FREE> ILLEGAL enterpriseService()");
  };

  private void throwExceptionIfInvalidLicense() throws InvalidLicenseException {
    if(!LicenseValidator.getInstance().isLicenseValid(license)) throw new InvalidLicenseException();
  }
}
