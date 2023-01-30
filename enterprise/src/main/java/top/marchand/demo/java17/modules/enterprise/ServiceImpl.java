package top.marchand.demo.java17.modules.enterprise;

import top.marchand.demo.java17.modules.contract.Implementation;
import top.marchand.demo.java17.modules.contract.License;
import top.marchand.demo.java17.modules.contract.LicenseLevel;
import top.marchand.demo.java17.modules.contract.Service;

@Implementation(level = LicenseLevel.ENTERPRISE)
public class ServiceImpl implements Service {
  private License license;
  public void freeService() {
    System.out.println("<ENTERPRISE> freeService()");
  };
  public void professionalService() {
    System.out.println("<ENTERPRISE> professionalService()");
  };
  public void enterpriseService() {
    System.out.println("<ENTERPRISE> enterpriseService()");
  };
}
