package top.marchand.demo.java17.modules.free;

import top.marchand.demo.java17.modules.contract.Implementation;
import top.marchand.demo.java17.modules.contract.License;
import top.marchand.demo.java17.modules.contract.LicenseLevel;
import top.marchand.demo.java17.modules.contract.Service;

@Implementation(level = LicenseLevel.FREE)
public class ServiceImpl implements Service {
  private License license;
  public void freeService() {
    System.out.println("<FREE> freeService()");
  };
  public void professionalService() {
    System.out.println("<FREE> ILLEGAL professionalService()");
  };
  public void enterpriseService() {
    System.out.println("<FREE> ILLEGAL enterpriseService()");
  };
}
