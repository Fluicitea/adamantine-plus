package shadowvappy.adamantineplus.tinkers.materials;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.smeltery.block.BlockTinkerFluid;

public class Fluids {
    public static FluidMolten fluidMoltenAdamantine;
    public static Block moltenAdamantine;

    public static void registerFluids() {
        fluidMoltenAdamantine = new FluidMolten("adamantine", 0x00ffff);
        fluidMoltenAdamantine.setTemperature(1000);
        FluidRegistry.registerFluid(fluidMoltenAdamantine);
        FluidRegistry.addBucketForFluid(fluidMoltenAdamantine);

        moltenAdamantine = new BlockTinkerFluid(fluidMoltenAdamantine, Material.LAVA);
    }
}