package top.marchand.demo.java17.modules;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import top.marchand.demo.java17.modules.contract.License;
import top.marchand.demo.java17.modules.contract.Service;
import top.marchand.demo.java17.modules.licenses.LicenseManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessorIT {

  @Test
  public void given_no_license_getService_should_return_FreeServiceImpl() throws Exception {
    // given
    resetLicenseManager();
    // when
    Service actual = Processor.newProcessor().getService();
    // then
    Assertions.assertThat(actual.getClass()).isSameAs(top.marchand.demo.java17.modules.free.ServiceImpl.class);
  }
  @Test
  public void given_free_license_getService_should_return_FreeServiceImpl() throws Exception {
    // given
    resetLicenseManager();
    License license = getLicense("license-free.txt");
    LicenseManager mock = Mockito.mock(LicenseManager.class);
    Mockito.when(mock.getLicense()).thenReturn(license);
    try(MockedStatic<LicenseManager> utilities = Mockito.mockStatic(LicenseManager.class)) {
      utilities.when(LicenseManager::getInstance).thenReturn(mock);
    }
    // when
    Service actual = Processor.newProcessor().getService();
    // then
    Assertions.assertThat(actual.getClass()).isSameAs(top.marchand.demo.java17.modules.free.ServiceImpl.class);
  }
  @Test
  public void given_free_license_getAllImplementationsValidForLicense_should_return_1() throws Exception {
    // given
    resetLicenseManager();
    License license = getLicense("license-free.txt");
    // when
    Stream<ServiceLoader.Provider<Service>> actual = Processor.newProcessor().getAllImplementationsValidForLicense(license);
    // then
    Assertions.assertThat(actual).hasSize(1);
  }
  @Test
  public void given_professional_license_getAllImplementationsValidForLicense_should_return_2() throws Exception {
    // given
    resetLicenseManager();
    License license = getLicense("license-professional.txt");
    // when
    Stream<ServiceLoader.Provider<Service>> actual = Processor.newProcessor().getAllImplementationsValidForLicense(license);
    // then
    Assertions.assertThat(actual).hasSize(2);
  }
  @Test
  public void given_enterprise_license_getAllImplementationsValidForLicense_should_return_3() throws Exception {
    // given
    resetLicenseManager();
    License license = getLicense("license-enterprise.txt");
    // when
    Stream<ServiceLoader.Provider<Service>> actual = Processor.newProcessor().getAllImplementationsValidForLicense(license);
    // then
    Assertions.assertThat(actual).hasSize(3);
  }
  @Test
  public void given_enterprise_license_getAllImplementationsValidForLicense_should_return_all_3_implems() throws Exception {
    // given
    resetLicenseManager();
    License license = getLicense("license-enterprise.txt");
    List<? extends Class<? extends Service>> expected = Arrays.asList(
        top.marchand.demo.java17.modules.free.ServiceImpl.class,
        top.marchand.demo.java17.modules.professional.ServiceImpl.class,
        top.marchand.demo.java17.modules.enterprise.ServiceImpl.class);
    // when
    List<? extends Class<? extends Service>> actual = Processor
        .newProcessor()
        .getAllImplementationsValidForLicense(license)
        .map(serviceProvider -> serviceProvider.type())
        .collect(Collectors.toList());
    // then
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(actual).hasSize(3);
    boolean freeOk=false, professionalOk=false, enterpriseOk=false;
    for (var c: actual) {
      switch(c.getName()) {
        case "top.marchand.demo.java17.modules.free.ServiceImpl" -> freeOk=true;
        case "top.marchand.demo.java17.modules.professional.ServiceImpl" -> professionalOk=true;
        case "top.marchand.demo.java17.modules.enterprise.ServiceImpl" -> enterpriseOk=true;
        default -> System.err.println(c.getClass().getName());
      }
    }
    softly.assertThat(freeOk).as("services should contain free service").isTrue();
    softly.assertThat(professionalOk).as("services should contain professional service").isTrue();
    softly.assertThat(enterpriseOk).as("services should contain enterprise service").isTrue();
    softly.assertAll();
  }

  @Test
  public void given_professional_license_getService_should_return_ProfessionalServiceImpl() throws Exception {
    // given
    resetLicenseManager();
    License license = getLicense("license-professional.txt");
    LicenseManager mock = Mockito.mock(LicenseManager.class);
    Mockito.when(mock.getLicense()).thenReturn(license);
    try(MockedStatic<LicenseManager> utilities = Mockito.mockStatic(LicenseManager.class)) {
      utilities.when(LicenseManager::getInstance).thenReturn(mock);
      // when
      Service actual = Processor.newProcessor().getService();
      // then
      SoftAssertions softly = new SoftAssertions();
      softly.assertThat(actual.getClass()).isSameAs(top.marchand.demo.java17.modules.professional.ServiceImpl.class);
      softly.assertThat(getServiceLicense(actual)).isSameAs(license);
    }
  }
  @Test
  public void given_enterprise_license_getService_should_return_EnterpriseServiceImpl() throws Exception {
    // given
    resetLicenseManager();
    License license = getLicense("license-enterprise.txt");
    LicenseManager mock = Mockito.mock(LicenseManager.class);
    Mockito.when(mock.getLicense()).thenReturn(license);
    try(MockedStatic<LicenseManager> utilities = Mockito.mockStatic(LicenseManager.class)) {
      utilities.when(LicenseManager::getInstance).thenReturn(mock);
      // when
      Service actual = Processor.newProcessor().getService();
      // then
      SoftAssertions softly = new SoftAssertions();
      softly.assertThat(actual.getClass()).isSameAs(top.marchand.demo.java17.modules.enterprise.ServiceImpl.class);
      softly.assertThat(getServiceLicense(actual)).isSameAs(license);
    }
  }

  private License getServiceLicense(Service actual) throws IllegalAccessException, NoSuchFieldException {
    Field field = actual.getClass().getDeclaredField("license");
    field.setAccessible(true);
    return (License)field.get(actual);
  }

  private License getLicense(String fileName) throws NoSuchMethodException, URISyntaxException, InvocationTargetException, IllegalAccessException {
    Method method = LicenseManager.class.getDeclaredMethod("parseLicense", URI.class);
    method.setAccessible(true);
    URI uri = getClass().getResource("/"+fileName).toURI();
    return (License)method.invoke(null, uri);
  }

  private void resetLicenseManager() throws NoSuchFieldException, IllegalAccessException {
    Field field = LicenseManager.class.getDeclaredField("instance");
    field.setAccessible(true);
    field.set(null, null);
    field.setAccessible(false);
  }
}
