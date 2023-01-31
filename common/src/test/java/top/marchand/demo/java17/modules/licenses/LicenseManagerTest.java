package top.marchand.demo.java17.modules.licenses;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import top.marchand.demo.java17.modules.UnparsableLicenseException;
import top.marchand.demo.java17.modules.contract.License;

import java.net.URISyntaxException;
import java.net.URL;

public class LicenseManagerTest {

  @Test
  public void getInstance_should_return_an_instance() {
    LicenseManager actual = LicenseManager.getInstance();
    Assertions.assertThat(actual).isNotNull();
  }
  @Test
  public void given_no_license_loadLicense_should_return_DEFAULT_LICENSE() throws UnparsableLicenseException {
    // given
    License expected = License.DEFAULT_LICENSE;
    // when
    License actual = LicenseManager.loadLicense();
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  public void given_translatedLicense_parseLicense_should_throw() throws URISyntaxException, UnparsableLicenseException {
    URL url = getClass().getResource("/invalid/translated-license.txt");
    ThrowableAssert.catchThrowableOfType(() -> LicenseManager.parseLicense(url.toURI()), UnparsableLicenseException.class);
  }
  @Test
  public void given_invalidLevelLicense_parseLicense_should_throw() throws URISyntaxException, UnparsableLicenseException {
    URL url = getClass().getResource("/invalid/invalidLevel-license.txt");
    ThrowableAssert.catchThrowableOfType(() -> LicenseManager.parseLicense(url.toURI()), UnparsableLicenseException.class);
  }
  @Test
  public void given_free_license_parseLicense_should_return_Free_level() throws URISyntaxException, UnparsableLicenseException {
    // given
    URL url = getClass().getResource("/license-free.txt");
    // when
    License actual = LicenseManager.parseLicense(url.toURI());
    // then
    Assertions.assertThat(actual.level()).isEqualTo(License.LicenseLevel.FREE);
  }
  @Test
  public void given_professional_license_parseLicense_should_return_Professional_level() throws URISyntaxException, UnparsableLicenseException {
    // given
    URL url = getClass().getResource("/license-professional.txt");
    // when
    License actual = LicenseManager.parseLicense(url.toURI());
    // then
    Assertions.assertThat(actual.level()).isEqualTo(License.LicenseLevel.PROFESSIONAL);
  }
  @Test
  public void given_enterprise_license_parseLicense_should_return_Enterprise_level() throws URISyntaxException, UnparsableLicenseException {
    // given
    URL url = getClass().getResource("/license-enterprise.txt");
    // when
    License actual = LicenseManager.parseLicense(url.toURI());
    // then
    Assertions.assertThat(actual.level()).isEqualTo(License.LicenseLevel.ENTERPRISE);
  }
  @Test
  public void given_license_parseLicense_should_return_Christophe_Marchand_licensee() throws URISyntaxException, UnparsableLicenseException {
    // given
    URL url = getClass().getResource("/license-enterprise.txt");
    // when
    License actual = LicenseManager.parseLicense(url.toURI());
    // then
    Assertions.assertThat(actual.licensee()).isEqualTo("Christophe Marchand");
  }
  @Test
  public void given_enterprise_license_parseLicense_should_return_correct_checksum() throws URISyntaxException, UnparsableLicenseException {
    // given
    URL url = getClass().getResource("/license-enterprise.txt");
    // when
    License actual = LicenseManager.parseLicense(url.toURI());
    // then
    Assertions.assertThat(actual.checksum()).isEqualTo("oJkDezVmwwvsjp4Nfq3Q0JsrtuiEgHv2ny3XygaM8Ik=");
  }

}
