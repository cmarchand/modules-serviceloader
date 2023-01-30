package top.marchand.demo.java17.modules.contract;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Implementation {
  License.LicenseLevel level() default License.LicenseLevel.FREE;
}
