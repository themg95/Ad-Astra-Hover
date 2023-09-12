package earth.terrarium.adastra.common.utils;

import earth.terrarium.adastra.common.blocks.base.MachineBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class ModUtils {

    public static <T extends ParticleOptions> void sendParticles(ServerLevel level, T particle, double x, double y, double z, int count, double deltaX, double deltaY, double deltaZ, double speed) {
        for (ServerPlayer player : level.players()) {
            level.sendParticles(player, particle, true, x, y, z, count, deltaX, deltaY, deltaZ, speed);
        }
    }

    // Marks this player's velocity as changed, so that it can be re-synced with the client later
    public static void sendUpdatePacket(ServerPlayer player) {
        player.hurtMarked = true;
    }

    public static Direction relative(BlockEntity from, Direction to) {
        return relative(from.getBlockState().getValue(MachineBlock.FACING), to);
    }

    public static Direction relative(Direction from, Direction to) {
        if (to.getAxis().isVertical()) return to;
        return switch (from) {
            case EAST -> to.getClockWise();
            case SOUTH -> to.getOpposite();
            case WEST -> to.getCounterClockWise();
            default -> to;
        };
    }
}