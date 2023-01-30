package top.marchand.demo.java17.modules.contract;

public interface Service {
  void freeService() throws InvalidLicenseException;
  void professionalService() throws InvalidLicenseException;
  void enterpriseService() throws InvalidLicenseException;
}
