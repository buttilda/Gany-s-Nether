package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.entities.EntityLightningBall;
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

	public SceptreOfLightning() {
		super();
		setMaxDamage(GanysNether.sceptreOfLightningDurability);
		setTextureName(Utils.getItemTexture(Strings.Items.SCEPTRE_OF_LIGHTNING_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.SCEPTRE_OF_LIGHTNING_NAME));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.swingItem();
		if (!world.isRemote)
			world.spawnEntityInWorld(new EntityLightningBall(world, player));
		stack.damageItem(1, player);
		if (stack.stackSize <= 0)
			player.setCurrentItemOrArmor(0, null);
		return stack;
	}

	@Override
	public boolean getIsRepairable(ItemStack item, ItemStack material) {
		return material.getItem() == ModItems.sceptreCap && material.getItemDamage() == 1;
	}
}