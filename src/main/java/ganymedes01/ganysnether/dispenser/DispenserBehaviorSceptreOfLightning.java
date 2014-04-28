package ganymedes01.ganysnether.dispenser;

import ganymedes01.ganysnether.entities.EntityLightningBall;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
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

public class DispenserBehaviorSceptreOfLightning extends BehaviorDefaultDispenseItem {

	@Override
	public ItemStack dispenseStack(IBlockSource block, ItemStack stack) {
		World world = block.getWorld();
		IPosition iposition = BlockDispenser.func_149939_a(block);
		EnumFacing enumfacing = BlockDispenser.func_149937_b(block.getBlockMetadata());

		IProjectile iprojectile = new EntityLightningBall(world, iposition.getX(), iposition.getY(), iposition.getZ());
		iprojectile.setThrowableHeading(enumfacing.getFrontOffsetX(), enumfacing.getFrontOffsetY() + 0.1F, enumfacing.getFrontOffsetZ(), 1.1F, 6.0F);
		world.spawnEntityInWorld((Entity) iprojectile);

		damageStack(world, stack);
		return stack;
	}

	private void damageStack(World world, ItemStack stack) {
		EntityPlayer player = FakePlayerFactory.getMinecraft((WorldServer) world);
		player.setCurrentItemOrArmor(0, stack);
		stack.damageItem(1, player);
	}
}