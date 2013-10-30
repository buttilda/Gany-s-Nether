package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
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
		super(ModIDs.BOTTOMLESS_BUCKET_ID);
		setMaxStackSize(1);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.BOTTOMLESS_BUCKET_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.BOTTOMLESS_BUCKET_NAME));
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
			else if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
				int x = movingobjectposition.blockX;
				int y = movingobjectposition.blockY;
				int z = movingobjectposition.blockZ;
				if (world.getBlockMaterial(x, y, z) == Material.water || world.getBlockMaterial(x, y, z) == Material.lava)
					world.setBlockToAir(x, y, z);
			}
		}
		return stack;
	}
}