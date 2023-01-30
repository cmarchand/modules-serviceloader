package top.marchand.demo.java17.modules.contract.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import top.marchand.demo.java17.modules.contract.License;

public class ShadedLicenseValidatorTest {

  @Test
  public void given_christophe_and_free_checksum_should_be_xxx() {
    // given
    License license = new License("Christophe Marchand", License.LicenseLevel.FREE, "xxx");
    String expected = "Fq+wsDKu3U3R/FunSLQ3nuLGGBQah4YZ0TRpBfLVF3U=";
    // when
    String actual = ShadedLicenseValidator.getInstance().calculateChecksum(license);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  public void given_christophe_and_profesional_checksum_should_be_xxx() {
    // given
    License license = new License("Christophe Marchand", License.LicenseLevel.PROFESSIONAL, "xxx");
    String expected = "51URaSb83g6OaJsSxTYERFhuTpw/APS2jfhRLeT409s=";
    // when
    String actual = ShadedLicenseValidator.getInstance().calculateChecksum(license);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  public void given_christophe_and_enterprise_checksum_should_be_xxx() {
    // given
    License license = new License("Christophe Marchand", License.LicenseLevel.ENTERPRISE, "xxx");
    String expected = "oJkDezVmwwvsjp4Nfq3Q0JsrtuiEgHv2ny3XygaM8Ik=";
    // when
    String actual = ShadedLicenseValidator.getInstance().calculateChecksum(license);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  public void given_defaultLicense_checksum_should_be_xxx() {
    // given
    License license = License.DEFAULT_LICENSE;
    String expected = "jJadj4UPvLmo2Cf4xsaDGAE2BR14JCR/gnvrcUwxHqo=";
    // when
    String actual = ShadedLicenseValidator.getInstance().calculateChecksum(license);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
}
