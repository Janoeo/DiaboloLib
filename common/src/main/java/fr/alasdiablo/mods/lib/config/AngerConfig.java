package fr.alasdiablo.mods.lib.config;

import com.google.gson.JsonObject;
import fr.alasdiablo.mods.lib.Constants;
import fr.alasdiablo.mods.lib.api.config.JsonConfig;
import org.jetbrains.annotations.NotNull;

public class AngerConfig extends JsonConfig {

    private final String elementName;

    private int     angerRange;
    private boolean canAnger;

    public AngerConfig(String elementName) {
        this.elementName = elementName;
        this.canAnger    = true;
        this.angerRange  = 10;
    }

    public int getAngerRange() {
        return this.angerRange;
    }

    public boolean canAnger() {
        return this.canAnger;
    }

    @Override
    protected void read(@NotNull JsonObject json) {
        this.angerRange = json.get("anger_range").getAsInt();
        this.canAnger   = json.get("can_anger").getAsBoolean();
        Constants.LOG.debug("Data change for {}, canAnger : {}, angerRange : {}", this.elementName, this.canAnger, this.angerRange);
    }

    @Override
    protected JsonObject write() {
        JsonObject json = new JsonObject();
        json.addProperty("anger_range", this.angerRange);
        json.addProperty("can_anger", this.canAnger);
        return json;
    }

    @Override
    protected String getName() {
        return this.elementName;
    }
}
