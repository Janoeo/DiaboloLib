package fr.alasdiablo.diolib;

import fr.alasdiablo.diolib.config.DiaboloLibConfig;
import fr.alasdiablo.diolib.event.FireworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DiaboloLib.MOD_ID)
public class DiaboloLib {

    public static final String MOD_ID = "diolib";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public DiaboloLib() {
        MinecraftForge.EVENT_BUS.addListener(FireworkEvent.FIREWORK_EVENT::onEvent);
        DiaboloLibConfig.init();
    }
}
