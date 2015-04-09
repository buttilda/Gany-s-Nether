package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class BottomlessBucket extends ItemSimpleFoiled {

	public BottomlessBucket() {
		setMaxStackSize(1);
		setTextureName(Utils.getItemTexture(Strings.Items.BOTTOMLESS_BUCKET_NAME));
		setCreativeTab(GanysNether.enableBottomlessBucket ? GanysNether.netherTab : null);
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.BOTTOMLESS_BUCKET_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(world, player, true);
			if (movingobjectposition == null)
				return stack;
			else if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
				int x = movingobjectposition.blockX;
				int y = movingobjectposition.blockY;
				int z = movingobjectposition.blockZ;
				if (world.getBlock(x, y, z).getMaterial() == Material.water || world.getBlock(x, y, z).getMaterial() == Material.lava)
					world.setBlockToAir(x, y, z);
			}
		}
		return stack;
	}
}