package top.marchand.demo.java17.modules;

public class UnparsableLicenseException extends Exception {
  public UnparsableLicenseException() {
  }

  public UnparsableLicenseException(String message) {
    super(message);
  }

  public UnparsableLicenseException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnparsableLicenseException(Throwable cause) {
    super(cause);
  }

  public UnparsableLicenseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
