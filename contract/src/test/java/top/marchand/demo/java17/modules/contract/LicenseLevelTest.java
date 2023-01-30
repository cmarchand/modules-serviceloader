package top.marchand.demo.java17.modules.contract;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LicenseLevelTest {

  @Test
  public void free_should_be_lesser_than_professional() {
    Assertions.assertThat(LicenseLevel.FREE).isLessThan(LicenseLevel.PROFESSIONAL);
  }
  @Test
  public void professional_should_be_lesser_than_enterprise() {
    Assertions.assertThat(LicenseLevel.PROFESSIONAL).isLessThan(LicenseLevel.ENTERPRISE);
  }
  @Test
  public void free_should_be_lesser_than_enterprise() {
    Assertions.assertThat(LicenseLevel.FREE).isLessThan(LicenseLevel.ENTERPRISE);
  }
}
