package fr.alasdiablo.mods.lib.block;

/**
 * Helper class providing utility methods and constants for working with Minecraft blocks.
 * This class includes functionality for fire mechanics, block stripping, and particle effects.
 */
@SuppressWarnings("unused")
public class BlockHelper {

    /**
     * Time in ticks for instant block ignition
     */
    public static final int IGNITE_INSTANT = 60;
    /**
     * Time in ticks for easy block ignition
     */
    public static final int IGNITE_EASY = 30;
    /**
     * Time in ticks for medium block ignition
     */
    public static final int IGNITE_MEDIUM = 15;
    /**
     * Time in ticks for hard block ignition
     */
    public static final int IGNITE_HARD = 5;

    /**
     * Duration in ticks for instant block burning
     */
    public static final int BURN_INSTANT = 100;
    /**
     * Duration in ticks for easy block burning
     */
    public static final int BURN_EASY = 60;
    /**
     * Duration in ticks for medium block burning
     */
    public static final int BURN_MEDIUM = 20;
    /**
     * Duration in ticks for hard block burning
     */
    public static final int BURN_HARD = 5;

    /**
     * Encouragement value for wooden plank blocks to catch fire
     */
    public static final int PLANKS_ENCOURAGEMENT = 5;
    /**
     * Flammability value determining how quickly wooden plank blocks burn
     */
    public static final int PLANKS_FLAMMABILITY = 20;

    /**
     * Encouragement value for slab blocks to catch fire
     */
    public static final int SLAB_ENCOURAGEMENT = 5;
    /**
     * Flammability value determining how quickly a slab block burns
     */
    public static final int SLAB_FLAMMABILITY = 20;

    /**
     * Encouragement value for fence gate blocks to catch fire
     */
    public static final int FENCE_GATE_ENCOURAGEMENT = 5;
    /**
     * Flammability value determining how quickly fence gate blocks burn
     */
    public static final int FENCE_GATE_FLAMMABILITY = 20;

    /**
     * Encouragement value for fence blocks to catch fire
     */
    public static final int FENCE_ENCOURAGEMENT = 5;
    /**
     * Flammability value determining how quickly fence blocks burn
     */
    public static final int FENCE_FLAMMABILITY = 20;

    /**
     * Encouragement value for stair blocks to catch fire
     */
    public static final int STAIRS_ENCOURAGEMENT = 5;
    /**
     * Flammability value determining how quickly stair blocks burn
     */
    public static final int STAIRS_FLAMMABILITY = 20;

    /**
     * Encouragement value for log blocks to catch fire
     */
    public static final int LOG_ENCOURAGEMENT = 5;
    /**
     * Flammability value determining how quickly log blocks burn
     */
    public static final int LOG_FLAMMABILITY = 5;

    /**
     * Encouragement value for wood blocks to catch fire
     */
    public static final int WOOD_ENCOURAGEMENT = 5;
    /**
     * Flammability value determining how quickly wood blocks burn
     */
    public static final int WOOD_FLAMMABILITY = 5;

    /**
     * Encouragement value for leaf blocks to catch fire
     */
    public static final int LEAVES_ENCOURAGEMENT = 30;
    /**
     * Flammability value determining how quickly leaf blocks burn
     */
    public static final int LEAVES_FLAMMABILITY = 60;

    /**
     * Encouragement value for plant blocks to catch fire
     */
    public static final int PLANT_ENCOURAGEMENT = 60;
    /**
     * Flammability value determining how quickly a plant block burns
     */
    public static final int PLANT_FLAMMABILITY = 100;

    /**
     * Encouragement value for wool blocks to catch fire
     */
    public static final int WOOL_ENCOURAGEMENT = 30;
    /**
     * Flammability value determining how quickly wool blocks burn
     */
    public static final int WOOL_FLAMMABILITY = 60;

    /**
     * Encouragement value for carpet blocks to catch fire
     */
    public static final int CARPET_ENCOURAGEMENT = 60;
    /**
     * Flammability value determining how quickly carpet blocks burn
     */
    public static final int CARPET_FLAMMABILITY = 20;

//    /**
//     * Instance of the FireBlock used for managing block flammability
//     */
//    private static final FireBlock fireBlock = (FireBlock) Blocks.FIRE;
//
//    /**
//     * Sets custom flammability properties for a specified block.
//     * This method configures how easily a block catches fire and how quickly it burns.
//     *
//     * @param block         The target blocks to modify flammability properties
//     * @param encouragement The likelihood of the block catching fire (higher values mean easier ignition)
//     * @param flammability  The rate at which the block burns once ignited (higher values mean faster burning)
//     * @return The modified block instance
//     */
//    public static Block setFlammability(@NotNull Block block, int encouragement, int flammability) {
//        fireBlock.setFlammable(block, encouragement, flammability);
//        return block;
//    }
}