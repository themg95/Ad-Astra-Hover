package com.github.alexnijjar.ad_astra.registry;

import com.github.alexnijjar.ad_astra.AdAstra;
import com.github.alexnijjar.ad_astra.blocks.fluid.ModFluidAttributes;
import dev.architectury.core.fluid.ArchitecturyFlowingFluid;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;

public class ModFluids {

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(AdAstra.MOD_ID, Registry.FLUID_KEY);

	public static final RegistrySupplier<ArchitecturyFlowingFluid> OIL_STILL = FLUIDS.register("oil", () -> new ArchitecturyFlowingFluid.Source(ModFluidAttributes.OIL_FLUID_ATTRIBUTES));
	public static final RegistrySupplier<ArchitecturyFlowingFluid> FLOWING_OIL = FLUIDS.register("flowing_oil", () -> new ArchitecturyFlowingFluid.Flowing(ModFluidAttributes.OIL_FLUID_ATTRIBUTES));

	public static final RegistrySupplier<ArchitecturyFlowingFluid> FUEL_STILL = FLUIDS.register("fuel", () -> new ArchitecturyFlowingFluid.Source(ModFluidAttributes.FUEL_FLUID_ATTRIBUTES));
	public static final RegistrySupplier<ArchitecturyFlowingFluid> FLOWING_FUEL = FLUIDS.register("flowing_fuel", () -> new ArchitecturyFlowingFluid.Flowing(ModFluidAttributes.FUEL_FLUID_ATTRIBUTES));

	public static final RegistrySupplier<ArchitecturyFlowingFluid> CRYO_FUEL_STILL = FLUIDS.register("cryo_fuel", () -> new ArchitecturyFlowingFluid.Source(ModFluidAttributes.CRYO_FUEL_FLUID_ATTRIBUTES));
	public static final RegistrySupplier<ArchitecturyFlowingFluid> FLOWING_CRYO_FUEL = FLUIDS.register("flowing_cryo_fuel", () -> new ArchitecturyFlowingFluid.Flowing(ModFluidAttributes.CRYO_FUEL_FLUID_ATTRIBUTES));

	public static final RegistrySupplier<ArchitecturyFlowingFluid> OXYGEN_STILL = FLUIDS.register("oxygen", () -> new ArchitecturyFlowingFluid.Source(ModFluidAttributes.OXYGEN_FLUID_ATTRIBUTES));

	public static void register() {
		FLUIDS.register();
	}
}