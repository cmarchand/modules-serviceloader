package top.marchand.demo.java17.modules.contract;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LicenseLevelTest {

  @Test
  public void free_should_be_lesser_than_professional() {
    Assertions.assertThat(License.LicenseLevel.FREE).isLessThan(License.LicenseLevel.PROFESSIONAL);
  }
  @Test
  public void professional_should_be_lesser_than_enterprise() {
    Assertions.assertThat(License.LicenseLevel.PROFESSIONAL).isLessThan(License.LicenseLevel.ENTERPRISE);
  }
  @Test
  public void free_should_be_lesser_than_enterprise() {
    Assertions.assertThat(License.LicenseLevel.FREE).isLessThan(License.LicenseLevel.ENTERPRISE);
  }
}
