package shadowvappy.adamantineplus.tinkers.materials.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class Traits {
    public static final AbstractTrait adamantineEdge = new AdamantineEdgeTrait();
    public static final AbstractTrait durable = new DurableTrait(1);
    public static final AbstractArmorTrait adamantineResistance = new AdamantineResistanceTrait();
    public static final AbstractArmorTrait durableArmor = new DurableArmorTrait(1);
}
