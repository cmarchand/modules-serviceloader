package top.marchand.demo.java17.modules.enterprise;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import top.marchand.demo.java17.modules.contract.Implementation;
import top.marchand.demo.java17.modules.contract.License;

public class ServiceImplTest {
  @Test
  public void serviceImpl_should_have_annotation_with_level_enterprise() {
    Class clazz = ServiceImpl.class;
    SoftAssertions softly = new SoftAssertions();
    Implementation annotation = (Implementation) clazz.getAnnotation(Implementation.class);
    softly.assertThat(annotation).isNotNull();
    License.LicenseLevel level = annotation.level();
    softly.assertThat(level).isNotNull();
    softly.assertThat(level).isEqualTo(License.LicenseLevel.ENTERPRISE);
    softly.assertAll();
  }
}
