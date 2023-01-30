package top.marchand.demo.java17.modules.contract;

public record License (String licensee, LicenseLevel level, String checksum) {
  public static final License DEFAULT_LICENSE = new License("Free License", LicenseLevel.FREE, "jJadj4UPvLmo2Cf4xsaDGAE2BR14JCR/gnvrcUwxHqo=");
}
