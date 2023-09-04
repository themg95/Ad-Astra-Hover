package earth.terrarium.adastra.common.registry;


import earth.terrarium.adastra.AdAstra;
import earth.terrarium.botarium.common.registry.fluid.FluidData;
import earth.terrarium.botarium.common.registry.fluid.FluidProperties;
import earth.terrarium.botarium.common.registry.fluid.FluidRegistry;
import net.minecraft.resources.ResourceLocation;

public final class ModFluidProperties {
    public static final FluidRegistry FLUID_PROPERTIES = new FluidRegistry(AdAstra.MOD_ID);

    public static final FluidData OXYGEN = FLUID_PROPERTIES.register("oxygen", FluidProperties.create()
        .still(new ResourceLocation("minecraft:block/water_still"))
        .flowing(new ResourceLocation("minecraft:block/water_flow"))
        .overlay(new ResourceLocation("minecraft:block/water_overlay"))
        .screenOverlay(new ResourceLocation("textures/misc/underwater.png"))
        .viscosity(0)
        .density(-1)
        .disablePlacing()
        .tintColor(0xdae6f0)
        .canConvertToSource(false));

    public static final FluidData HYDROGEN = FLUID_PROPERTIES.register("hydrogen", FluidProperties.create()
        .still(new ResourceLocation("minecraft:block/water_still"))
        .flowing(new ResourceLocation("minecraft:block/water_flow"))
        .overlay(new ResourceLocation("minecraft:block/water_overlay"))
        .screenOverlay(new ResourceLocation("textures/misc/underwater.png"))
        .viscosity(0)
        .density(-1)
        .disablePlacing()
        .tintColor(0x89CFF0)
        .canConvertToSource(false));

    public static final FluidData OIL = FLUID_PROPERTIES.register("oil", FluidProperties.create()
        .still(new ResourceLocation("minecraft:block/water_still"))
        .flowing(new ResourceLocation("minecraft:block/water_flow"))
        .overlay(new ResourceLocation("minecraft:block/water_overlay"))
        .screenOverlay(new ResourceLocation("textures/misc/underwater.png"))
        .viscosity(2000)
        .density(2000)
        .tintColor(0x373A36)
        .canConvertToSource(false));
}