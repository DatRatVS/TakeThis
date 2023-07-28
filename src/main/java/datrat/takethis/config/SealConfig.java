package datrat.takethis.config;

import java.io.File;

public class SealConfig {

    private static com.focamacho.sealconfig.SealConfig sealConfig;
    public static SealConfigObject config;

    public static void loadConfig() {

        sealConfig = new com.focamacho.sealconfig.SealConfig();
        config = sealConfig.getConfig(new File("./plugins/TakeThis/config.json5"), SealConfigObject.class);

        saveConfig();

    }

    public static void reloadConfig() { sealConfig.reload(); }

    public static void saveConfig() { sealConfig.save(config); }

}
