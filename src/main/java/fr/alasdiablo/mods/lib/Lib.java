package fr.alasdiablo.mods.lib;

import fr.alasdiablo.mods.lib.event.FallDamageReductionEventHandler;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(Lib.MOD_ID)
public class Lib {
    public static final String MOD_ID = "dio_lib";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Lib(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.addListener(FallDamageReductionEventHandler::onLivingFall);
    }
}
