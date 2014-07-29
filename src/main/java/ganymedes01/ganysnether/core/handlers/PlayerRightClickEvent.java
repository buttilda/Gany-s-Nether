package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class PlayerRightClickEvent {

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.entityPlayer != null) {
			World world = event.entityPlayer.worldObj;
			if (event.action == Action.RIGHT_CLICK_BLOCK)
				if (world.getBlock(event.x, event.y, event.z) == Blocks.obsidian) {
					ItemStack current = event.entityPlayer.inventory.getCurrentItem();
					if (current != null && current.getItem() == Items.ghast_tear) {
						event.entityPlayer.swingItem();
						plantBlock(world, event.x, event.y, event.z, event.face);
						if (!event.entityPlayer.capabilities.isCreativeMode)
							event.entityPlayer.inventory.getCurrentItem().stackSize--;
					}
				}
		}
	}

	private void plantBlock(World world, int x, int y, int z, int face) {
		switch (face) {
			case 0:
			case 1:
				return;
			case 2:
				world.setBlock(x, y, z - 1, ModBlocks.weepingPod, 0, 3);
				break;
			case 3:
				world.setBlock(x, y, z + 1, ModBlocks.weepingPod, 2, 3);
				break;
			case 4:
				world.setBlock(x - 1, y, z, ModBlocks.weepingPod, 3, 3);
				break;
			case 5:
				world.setBlock(x + 1, y, z, ModBlocks.weepingPod, 1, 3);
				break;
		}
	}
}