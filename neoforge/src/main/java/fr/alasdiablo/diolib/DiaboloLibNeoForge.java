package fr.alasdiablo.diolib;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class DiaboloLibNeoForge {
    public DiaboloLibNeoForge(IEventBus eventBus) {
        CommonClass.init();

    }
}