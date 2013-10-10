package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.entities.EntityLightningBall;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SceptreOfLightning extends Sceptre {

	public static final int DEFAULT_DUR = 128;

	public SceptreOfLightning() {
		super(ModIDs.SCEPTRE_OF_LIGHTNING_ID);
		setTextureName(Utils.getItemTexture(Strings.SCEPTRE_OF_LIGHTNING_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SCEPTRE_OF_LIGHTNING_NAME));
		setMaxDamage(GanysNether.sceptreOfLightningDurability > 0 ? GanysNether.sceptreOfLightningDurability : DEFAULT_DUR);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.swingItem();
		if (!world.isRemote)
			world.spawnEntityInWorld(new EntityLightningBall(world, player));
		stack.damageItem(1, player);
		return stack;
	}
}