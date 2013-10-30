package ganymedes01.ganysnether.dispenser;

import ganymedes01.ganysnether.items.LivingSoul;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayerFactory;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class DispenserBehaviorLivingSoul extends BehaviorDefaultDispenseItem {

	@Override
	public ItemStack dispenseStack(IBlockSource block, ItemStack stack) {
		World world = block.getWorld();
		EnumFacing enumfacing = BlockDispenser.getFacing(block.getBlockMetadata());
		int x = block.getXInt() + enumfacing.getFrontOffsetX();
		int y = block.getYInt() + enumfacing.getFrontOffsetY();
		int z = block.getZInt() + enumfacing.getFrontOffsetZ();

		EntityPlayer player = FakePlayerFactory.getMinecraft(world);
		player.setCurrentItemOrArmor(0, stack);

		if (LivingSoul.applyBonemeal(stack, world, x, y, z, player))
			if (!world.isRemote)
				world.playAuxSFX(2005, x, y, z, 0);

		return stack;
	}
}