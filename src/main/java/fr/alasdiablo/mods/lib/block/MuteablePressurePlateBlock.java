package fr.alasdiablo.mods.lib.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class MuteablePressurePlateBlock extends PressurePlateBlock {
    private final boolean muted;

    public MuteablePressurePlateBlock(BlockSetType type, Properties properties, boolean muted) {
        super(type, properties);
        this.muted = muted;
    }

    protected void checkPressed(@Nullable Entity sourceEntity, @NonNull Level level, @NonNull BlockPos pos, @NonNull BlockState state, int oldSignal) {
        int signal = this.getSignalStrength(level, pos);
        boolean wasPressed = oldSignal > 0;
        boolean isPressed = signal > 0;
        if (oldSignal != signal) {
            BlockState newState = this.setSignalForState(state, signal);
            level.setBlock(pos, newState, 2);
            this.updateNeighbours(level, pos);
            level.setBlocksDirty(pos, state, newState);
        }

        if (!isPressed && wasPressed) {
            if (!this.muted) {
                level.playSound(null, pos, this.type.pressurePlateClickOff(), SoundSource.BLOCKS);
            }
            level.gameEvent(sourceEntity, GameEvent.BLOCK_DEACTIVATE, pos);
        } else if (isPressed && !wasPressed) {
            if (!this.muted) {
                level.playSound(null, pos, this.type.pressurePlateClickOn(), SoundSource.BLOCKS);
            }
            level.gameEvent(sourceEntity, GameEvent.BLOCK_ACTIVATE, pos);
        }

        if (isPressed) {
            level.scheduleTick(new BlockPos(pos), this, this.getPressedTime());
        }
    }
}
