package ganymedes01.ganysnether.dispenser;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class DispenserBehaviorSceptreOfWithering extends BehaviorDefaultDispenseItem {

	@Override
	public ItemStack dispenseStack(IBlockSource block, ItemStack stack) {
		EnumFacing enumfacing = BlockDispenser.func_149937_b(block.getBlockMetadata());
		IPosition iposition = BlockDispenser.func_149939_a(block);
		World world = block.getWorld();

		double x = iposition.getX() + enumfacing.getFrontOffsetX() * 0.3F;
		double y = iposition.getY() + enumfacing.getFrontOffsetX() * 0.3F;
		double z = iposition.getZ() + enumfacing.getFrontOffsetZ() * 0.3F;

		EntityWitherSkull witherskull = new EntityWitherSkull(world, x, y, z, enumfacing.getFrontOffsetX(), enumfacing.getFrontOffsetY(), enumfacing.getFrontOffsetZ());
		witherskull.setInvulnerable(true);
		world.spawnEntityInWorld(witherskull);
		damageStack(world, stack);

		return stack;
	}

	@Override
	protected void playDispenseSound(IBlockSource block) {
		block.getWorld().playAuxSFX(1014, block.getXInt(), block.getYInt(), block.getZInt(), 0);
	}

	private void damageStack(World world, ItemStack stack) {
		EntityPlayer player = FakePlayerFactory.getMinecraft((WorldServer) world);
		player.setCurrentItemOrArmor(0, stack);
		stack.damageItem(1, player);
	}
}