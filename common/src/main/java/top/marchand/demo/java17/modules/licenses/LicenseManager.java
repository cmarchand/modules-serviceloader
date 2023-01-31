package top.marchand.demo.java17.modules.licenses;

import top.marchand.demo.java17.modules.UnparsableLicenseException;
import top.marchand.demo.java17.modules.contract.License;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LicenseManager {
  private static LicenseManager instance;

  private LicenseManager() {

  }
  public static LicenseManager getInstance() {
    if(instance==null) {
      instance = new LicenseManager();
    }
    return instance;
  }

  static License parseLicense(URI resource) throws UnparsableLicenseException {
    String licensee = null, level = null, checksum = null;
    try {
      List<String> lines = Files.readAllLines(
          Path.of(resource),
          StandardCharsets.UTF_8);
      for (String line : lines) {
        if (isLicensee(line)) {
          licensee = extractValueFrom(line);
        } else if (isLevel(line)) {
          level = extractValueFrom(line);
        } else if (isChecksum(line)) {
          checksum = extractValueFrom(line);
        }
      }
      return new License(licensee, License.LicenseLevel.valueOf(level.toUpperCase()), checksum);
    } catch (IOException | NullPointerException | IllegalArgumentException ex) {
      throw new UnparsableLicenseException(resource.toString(), ex);
    }
  }

  private static boolean isLicensee(String line) {
    return line.startsWith("Licensee:");
  }

  private static boolean isLevel(String line) {
    return line.startsWith("Level:");
  }

  private static boolean isChecksum(String line) {
    return line.startsWith("Checksum:");
  }

  private static String extractValueFrom(String line) {
    return line.substring(line.indexOf(':') + 1).trim();
  }

  public License getLicense() throws UnparsableLicenseException {
    return loadLicense();
  }

  static License loadLicense() throws UnparsableLicenseException {
    URL resource = LicenseManager.class.getResource("license.txt");
    try {
      if (resource != null) {
        return parseLicense(resource.toURI());
      }
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    File f = new File("license.txt");
    if (f.exists()) {
      return parseLicense(f.toURI());
    }
    return License.DEFAULT_LICENSE;
  }
}
