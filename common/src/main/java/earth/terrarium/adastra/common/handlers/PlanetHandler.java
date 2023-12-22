package earth.terrarium.adastra.common.handlers;

import com.teamresourceful.resourcefullib.common.utils.SaveHandler;
import earth.terrarium.adastra.api.systems.GravityApi;
import earth.terrarium.adastra.api.systems.OxygenApi;
import earth.terrarium.adastra.api.systems.TemperatureApi;
import earth.terrarium.adastra.common.handlers.base.PlanetData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlanetHandler extends SaveHandler {
    private final Map<BlockPos, PlanetData> planetData = new HashMap<>();
    private final ServerLevel level;

    public PlanetHandler(ServerLevel level) {
        this.level = level;
    }

    @Override
    public void loadData(CompoundTag tag) {
        tag.getAllKeys().forEach(key ->
            planetData.put(BlockPos.of(Long.parseLong(key)), PlanetData.unpack(tag.getInt(key))));
    }

    @Override
    public void saveData(CompoundTag tag) {
        boolean defaultOxygen = OxygenApi.API.hasOxygen(level);
        short defaultTemperature = TemperatureApi.API.getTemperature(level);
        float defaultGravity = GravityApi.API.getGravity(level);
        planetData.forEach((pos, data) -> {
            // Don't save positions that are identical to the default planet values.
            if (data.oxygen() != defaultOxygen || data.temperature() != defaultTemperature || data.gravity() != defaultGravity) {
                tag.putInt(String.valueOf(pos.asLong()), data.pack());
            }
        });
    }

    public static PlanetHandler read(ServerLevel level) {
        return read(level.getDataStorage(), () -> new PlanetHandler(level), "adastra_planet_data");
    }

    public static boolean hasOxygen(ServerLevel level, BlockPos pos) {
        PlanetData data = read(level).planetData.get(pos);
        return data == null ? OxygenApi.API.hasOxygen(level) : data.oxygen();
    }

    public static short getTemperature(ServerLevel level, BlockPos pos) {
        PlanetData data = read(level).planetData.get(pos);
        return data == null ? TemperatureApi.API.getTemperature(level) : data.temperature();
    }

    public static float getGravity(ServerLevel level, BlockPos pos) {
        PlanetData data = read(level).planetData.get(pos);
        return data == null ? GravityApi.API.getGravity(level) : data.gravity();
    }

    public static void setOxygen(ServerLevel level, BlockPos pos, boolean oxygen) {
        var data = read(level);
        data.planetData.computeIfAbsent(pos, p -> new PlanetData(oxygen, getTemperature(level, p), getGravity(level, p)))
            .setOxygen(oxygen);
    }

    public static void setTemperature(ServerLevel level, BlockPos pos, short temperature) {
        var data = read(level);
        data.planetData.computeIfAbsent(pos, p -> new PlanetData(hasOxygen(level, p), temperature, getGravity(level, p)))
            .setTemperature(temperature);
    }

    public static void setGravity(ServerLevel level, BlockPos pos, float gravity) {
        var data = read(level);
        data.planetData.computeIfAbsent(pos, p -> new PlanetData(hasOxygen(level, p), getTemperature(level, p), gravity))
            .setGravity(gravity);
    }

    public static void setOxygen(ServerLevel level, Set<BlockPos> positions, boolean oxygen) {
        var data = read(level);
        for (BlockPos pos : positions) {
            data.planetData.computeIfAbsent(pos, p -> new PlanetData(oxygen, getTemperature(level, p), getGravity(level, p)))
                .setOxygen(oxygen);
        }
    }

    public static void setTemperature(ServerLevel level, Set<BlockPos> positions, short temperature) {
        var data = read(level);
        for (BlockPos pos : positions) {
            data.planetData.computeIfAbsent(pos, p -> new PlanetData(hasOxygen(level, p), temperature, getGravity(level, p)))
                .setTemperature(temperature);
        }
    }

    public static void setGravity(ServerLevel level, Set<BlockPos> positions, float gravity) {
        var data = read(level);
        for (BlockPos pos : positions) {
            data.planetData.computeIfAbsent(pos, p -> new PlanetData(hasOxygen(level, p), getTemperature(level, p), gravity))
                .setGravity(gravity);
        }
    }

    @Override
    public boolean isDirty() {
        return true;
    }
}