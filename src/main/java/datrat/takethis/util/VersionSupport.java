package datrat.takethis.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VersionSupport {

    private static final Pattern VERSION_PATTERN = Pattern.compile("(?<major>\\d+)\\.(?<minor>\\d+)(?:\\.(?<patch>\\d+))?");
    private static final int SUPPORTED_MAJOR = 1;
    private static final int MIN_SUPPORTED_MINOR = 20;
    private static final int MAX_SUPPORTED_MINOR = 21;

    private VersionSupport() {
    }

    public static Version detect() {
        String versionString = Bukkit.getBukkitVersion();
        Matcher matcher = VERSION_PATTERN.matcher(versionString);
        if (!matcher.find()) {
            return new Version(0, 0, 0, versionString);
        }

        int major = Integer.parseInt(matcher.group("major"));
        int minor = Integer.parseInt(matcher.group("minor"));
        int patch = matcher.group("patch") == null ? 0 : Integer.parseInt(matcher.group("patch"));

        return new Version(major, minor, patch, versionString);
    }

    public static boolean isSupported(Version version) {
        if (version.major != SUPPORTED_MAJOR) {
            return false;
        }

        return version.minor >= MIN_SUPPORTED_MINOR && version.minor <= MAX_SUPPORTED_MINOR;
    }

    public static void logCompatibility(JavaPlugin plugin) {
        Version current = detect();
        if (isSupported(current)) {
            plugin.getLogger().info(String.format("Detected supported Minecraft version %s", current));
        } else {
            plugin.getLogger().warning(String.format(
                    "Minecraft version %s is outside of the supported range 1.%d.x - 1.%d.x. Behaviour may vary.",
                    current.raw,
                    MIN_SUPPORTED_MINOR,
                    MAX_SUPPORTED_MINOR));
        }
    }

    public static final class Version {
        private final int major;
        private final int minor;
        private final int patch;
        private final String raw;

        private Version(int major, int minor, int patch, String raw) {
            this.major = major;
            this.minor = minor;
            this.patch = patch;
            this.raw = raw;
        }

        @Override
        public String toString() {
            if (patch == 0) {
                return major + "." + minor;
            }
            return major + "." + minor + '.' + patch;
        }
    }
}

