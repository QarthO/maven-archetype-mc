package gg.quartzdev.qtemplateplugin.storage;

import gg.quartzdev.qtemplateplugin.util.Messages;
import gg.quartzdev.qtemplateplugin.util.QLogger;
import gg.quartzdev.qtemplateplugin.util.QPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QConfiguration {
    private final String filePath;
    private final String schemaVersion = "1.0";
    private File file;
    private YamlConfiguration yamlConfiguration;

    public QConfiguration(String fileName){
        String fileSeparator = System.getProperty("file.separator");
        filePath =
                QPlugin.getPlugin().getDataFolder() +
                fileSeparator +
                fileName.replaceAll("/", fileSeparator);
        loadFile();
    }

    private void loadFile() {
        file = new File(filePath);
        try {
            if (file.createNewFile()) {
                QPlugin.getPlugin().saveResource(filePath, true);
                QLogger.info(Messages.FILE_CREATED);
            }
            yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            stampFile();
        } catch (IOException exception) {
            QLogger.error(Messages.ERROR_CREATE_FILE.parse("file", filePath));
        }
    }
    private void stampFile(){
        List<String> notes = new ArrayList<>();
        notes.add("Last loaded with " + QPlugin.getName() + " v" + QPlugin.getVersion());
        if(yamlConfiguration.contains("schema-version")) {
            yamlConfiguration.set("schema-version", this.schemaVersion);
        }
        yamlConfiguration.setComments("schema-version", notes);
        save();
    }

    public void save(){
        try {
            yamlConfiguration.save(file);
        } catch(IOException exception){
            QLogger.error(Messages.ERROR_SAVE_FILE.parse("file", filePath));
        }
    }

    public @Nullable Object get(String path){
        return yamlConfiguration.get(path);
    }

    /**
     * Parses string
     * @param path - location in the config
     * @return - the {@link Number} that is represented by the string value found at the given path. Will will return a {@link Number} of value 0 if unable to parse the string.
     */
    public @NotNull Number getNumber(String path){
        Object data = yamlConfiguration.get(path);

//       If data isn't found
        if(data == null){
            return 0;
        }
//        Convert to string and try parsing
        String rawData = data.toString();
        Number number = null;
        try {
            number = Float.parseFloat(rawData);
        } catch(NumberFormatException e) {
            try {
                number = Double.parseDouble(rawData);
            } catch(NumberFormatException e1) {
                try {
                    number = Integer.parseInt(rawData);
                } catch(NumberFormatException e2) {
                    try {
                        number = Long.parseLong(rawData);
                    } catch(NumberFormatException e3) {
                        return 0;
                    }
                }
            }
        }
        return number;
    }

    /**
     * Gets a ${@link String } at the given path in the configuration file.
     * @param path - the path in the configuration
     * @return - the string value found at the given path. Returns an empty string if no data found
     */
    public @NotNull String getString(String path){

        Object data = yamlConfiguration.get(path);

//       If data isn't found
        if(data == null){
            return "";
        }

        return data.toString();

    }

    public @Nullable EntityType getEntityType(String path){
        Object data = yamlConfiguration.get(path);

//       If data isn't found
        if(data == null){
            return null;
        }
//        Convert to string and try parsing
        String rawData = data.toString();

        try {
            return EntityType.valueOf(rawData);
        } catch(IllegalArgumentException e){
            return null;
        }
    }
}
