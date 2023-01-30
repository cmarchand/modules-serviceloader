package top.marchand.demo.java17.modules.enterprise;

import top.marchand.demo.java17.modules.contract.*;

@Implementation(level = License.LicenseLevel.ENTERPRISE)
public class ServiceImpl implements Service {
  private License license;
  @Override
  public void freeService() throws InvalidLicenseException {
    throwExceptionIfInvalidLicense();
    System.out.println("<ENTERPRISE> freeService()");
  };
  @Override
  public void professionalService() throws InvalidLicenseException {
    throwExceptionIfInvalidLicense();
    System.out.println("<ENTERPRISE> professionalService()");
  };
  @Override
  public void enterpriseService() throws InvalidLicenseException {
    throwExceptionIfInvalidLicense();
    System.out.println("<ENTERPRISE> enterpriseService()");
  };
  private void throwExceptionIfInvalidLicense() throws InvalidLicenseException {
    if(!LicenseValidator.getInstance().isLicenseValid(license)) throw new InvalidLicenseException();
  }
}
