package top.marchand.demo.java17.modules.contract;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import top.marchand.demo.java17.modules.contract.validator.ShadedLicenseValidator;

import java.lang.reflect.Field;

public class LicenseValidatorTest {

  @Test
  public void isValidLicense_should_delegate_to_ShadedLicenseValidator() throws Exception {
    // given
    ShadedLicenseValidator mockedValidator = Mockito.spy(ShadedLicenseValidator.getInstance());
    LicenseValidator sut = LicenseValidator.getInstance();
    Field shadedField = LicenseValidator.class.getDeclaredField("shaded");
    shadedField.setAccessible(true);
    shadedField.set(sut, mockedValidator);
    License license = License.DEFAULT_LICENSE;
    // when
    sut.isLicenseValid(License.DEFAULT_LICENSE);
    // then
    Mockito.verify(mockedValidator).isLicenseValid(license);
  }
}
