package shadowvappy.adamantineplus.tinkers.proxy;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import shadowvappy.adamantineplus.proxy.ClientProxy;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TinkerClientProxy extends ClientProxy {
    @Override
    public void registerFluidModel(Block fluidBlock, String name) {
        Item item = Item.getItemFromBlock(fluidBlock);
        ModelBakery.registerItemVariants(item);
        final ModelResourceLocation modelResourceLocation = new ModelResourceLocation("adamantineplus:fluid", name);
        ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);

        ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
                return modelResourceLocation;
            }
        });
    }
}