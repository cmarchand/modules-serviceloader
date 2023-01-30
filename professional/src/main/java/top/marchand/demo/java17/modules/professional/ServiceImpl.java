package top.marchand.demo.java17.modules.professional;

import top.marchand.demo.java17.modules.contract.Implementation;
import top.marchand.demo.java17.modules.contract.License;
import top.marchand.demo.java17.modules.contract.LicenseLevel;
import top.marchand.demo.java17.modules.contract.Service;

@Implementation(level = LicenseLevel.PROFESSIONAL)
public class ServiceImpl implements Service {
  private License license;
  public void freeService() {
    System.out.println("<PROFESSIONAL> freeService()");
  };
  public void professionalService() {
    System.out.println("<PROFESSIONAL> professionalService()");
  };
  public void enterpriseService() {
    System.out.println("<PROFESSIONAL> ILLEGAL enterpriseService()");
  };
}
