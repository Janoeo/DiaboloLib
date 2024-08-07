package fr.alasdiablo.mods.lib.api.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import fr.alasdiablo.mods.lib.Constants;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Path;

public abstract class JsonConfig {

    private Path filePath;

    public JsonConfig() {
        this.filePath = null;
    }

    void initOrLoad() throws IOException, IllegalStateException {
        if (filePath == null) {
            throw new IllegalStateException("File path of " + this.getName() + " is null !");
        } else {
            try {
                FileReader fileReader = new FileReader(this.filePath.toString());
                JsonObject json       = JsonParser.parseReader(fileReader).getAsJsonObject();
                this.read(json);
                fileReader.close();
                Constants.LOG.debug(String.format("Config %s have been loaded", this.getName()));
            } catch (FileNotFoundException e) {
                this.preWrite();
                Constants.LOG.debug(String.format("Config %s have been write and loaded", this.getName()));
            } catch (JsonParseException | IllegalArgumentException | NullPointerException e) {
                Constants.LOG.warn(String.format("Error during config initialization on %s cause by: %s", this.getName(), e.getLocalizedMessage()));
                this.preWrite();
                Constants.LOG.debug(String.format("Config %s have been rewrite and loaded", this.getName()));
            }
        }
    }

    private void postWrite(@NotNull JsonObject json) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath.toString());

        StringWriter stringWriter = new StringWriter();
        JsonWriter   jsonWriter   = new JsonWriter(stringWriter);
        jsonWriter.setLenient(true);
        jsonWriter.setIndent("  ");
        Streams.write(json, jsonWriter);

        fileWriter.write(stringWriter.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    private void preWrite() throws IOException, IllegalStateException {
        if (filePath == null) {
            throw new IllegalStateException("File path of " + this.getName() + " is null !");
        } else {
            JsonObject json = this.write();
            this.postWrite(json);
        }
    }

    /**
     * Processes this json file
     *
     * @param json Json file need to be processes
     */
    protected abstract void read(JsonObject json);

    /**
     * Create the json object need to be written in the config directory
     *
     * @return JsonObject need to be written
     */
    protected abstract JsonObject write();

    protected abstract String getName();

    void filePath(Path path) {
        this.filePath = path;
    }

}
