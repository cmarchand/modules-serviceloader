package top.marchand.demo.java17.modules.contract;

public class InvalidLicenseException extends Exception {
  public InvalidLicenseException() {
  }

  public InvalidLicenseException(String message) {
    super(message);
  }

  public InvalidLicenseException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidLicenseException(Throwable cause) {
    super(cause);
  }

  public InvalidLicenseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
