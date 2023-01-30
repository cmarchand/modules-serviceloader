package top.marchand.demo.java17.modules;

import top.marchand.demo.java17.modules.contract.Implementation;
import top.marchand.demo.java17.modules.contract.License;
import top.marchand.demo.java17.modules.contract.LicenseLevel;
import top.marchand.demo.java17.modules.contract.Service;
import top.marchand.demo.java17.modules.licenses.LicenseManager;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Processor {

  private Processor() {}
  public static Processor newProcessor() {
    Processor processor = new Processor();
    return processor;
  }

  public Service getService() throws UnparsableLicenseException {
    ServiceLoader<Service> serviceLoader = getServices();
    License license = LicenseManager.getInstance().getLicense();
    Service service = getAllImplementationsValidForLicense(license)
        .sorted(getComparator())
        .findFirst()
        .orElseThrow()
        .get();
    setLicenseInService(service, license);
    return service;
  }

  Stream<ServiceLoader.Provider<Service>> getAllImplementationsValidForLicense(License license) {
    Stream<ServiceLoader.Provider<Service>> providerStream = getServices()
        .stream()
        .filter(serviceProvider -> license.level().compareTo(getLicenseLevel(serviceProvider.type())) >= 0);
    return providerStream;
  }

  ServiceLoader<Service> getServices() {
    ServiceLoader<Service> serviceLoader = ServiceLoader.load(Service.class);
    return serviceLoader;
  }

  private void setLicenseInService(Service service, License license) {
    try {
      Field field = service.getClass().getDeclaredField("license");
      field.setAccessible(true);
      field.set(service, license);
      field.setAccessible(false);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private Comparator<? super ServiceLoader.Provider<Service>> getComparator() {
    return (Comparator<ServiceLoader.Provider<Service>>) (o1, o2) -> {
      LicenseLevel l1 = getLicenseLevel(o1.type());
      LicenseLevel l2 = getLicenseLevel(o2.type());
      return l2.compareTo(l1);
    };
  }

  private LicenseLevel getLicenseLevel(Class<? extends Service> type) {
    Implementation annotation = type.getAnnotation(Implementation.class);
    return annotation.level();
  }


}
