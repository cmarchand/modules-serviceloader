package top.marchand.demo.java17.modules.free;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import top.marchand.demo.java17.modules.contract.Implementation;
import top.marchand.demo.java17.modules.contract.LicenseLevel;

public class ServiceImplTest {
  @Test
  public void serviceImpl_should_have_annotation_with_level_free() {
    Class clazz = ServiceImpl.class;
    SoftAssertions softly = new SoftAssertions();
    Implementation annotation = (Implementation) clazz.getAnnotation(Implementation.class);
    softly.assertThat(annotation).isNotNull();
    LicenseLevel level = annotation.level();
    softly.assertThat(level).isNotNull();
    softly.assertThat(level).isEqualTo(LicenseLevel.FREE);
    softly.assertAll();
  }
}
