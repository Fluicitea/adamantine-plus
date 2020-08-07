package shadowvappy.adamantineplus.blocks.machines.slots;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import shadowvappy.adamantineplus.blocks.machines.weaver.WaferWeaverRecipes;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SlotWeaverOutput extends SlotItemHandler {
    private final EntityPlayer player;
    private int removeCount;

    public SlotWeaverOutput(EntityPlayer player, IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
        this.player = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        this.onCrafting(stack, stack.getCount());
        super.onTake(thePlayer, stack);
        return stack;
    }

    @Override
    protected void onCrafting(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.onCrafting(stack);
    }

    @Override
    protected void onCrafting(ItemStack stack) {
        stack.onCrafting(this.player.world, this.player, this.removeCount);
        if(!this.player.world.isRemote){
            int i = this.removeCount;
            float f = WaferWeaverRecipes.getInstance().getWeavingExperience(stack);

            if(f==0)
                i = 0;
            else if(f!=1.0F) {
                int j = MathHelper.floor(i*f);
                if(j<MathHelper.ceil(i*f) && Math.random() < i*f-j)
                    ++j;
                i = j;
            }
            while(i>0) {
                int k = EntityXPOrb.getXPSplit(i);
                i-=k;
                this.player.world.spawnEntity(new EntityXPOrb(this.player.world, this.player.posX, this.player.posY+0.5D, this.player.posZ+0.5D, k));
            }
        }
    }

    @Override
    public ItemStack decrStackSize(int amount) {
        return super.decrStackSize(amount);
    }
}