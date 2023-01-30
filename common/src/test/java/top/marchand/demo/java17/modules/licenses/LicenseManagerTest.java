package top.marchand.demo.java17.modules.licenses;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LicenseManagerTest {

  @Test
  public void getInstance_should_return_an_instance() {
    LicenseManager actual = LicenseManager.getInstance();
    Assertions.assertThat(actual).isNotNull();
  }
}
