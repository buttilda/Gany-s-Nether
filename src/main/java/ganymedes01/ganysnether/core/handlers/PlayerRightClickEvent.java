package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class PlayerRightClickEvent {

	@ForgeSubscribe
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.entityPlayer != null) {
			World world = event.entityPlayer.worldObj;
			if (event.action == Action.RIGHT_CLICK_BLOCK)
				if (world.getBlockId(event.x, event.y, event.z) == Block.obsidian.blockID) {
					ItemStack current = event.entityPlayer.inventory.getCurrentItem();
					if (current != null && current.getItem() == Item.ghastTear) {
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
				world.setBlock(x, y, z - 1, ModBlocks.weepingPod.blockID, 0, 3);
				break;
			case 3:
				world.setBlock(x, y, z + 1, ModBlocks.weepingPod.blockID, 2, 3);
				break;
			case 4:
				world.setBlock(x - 1, y, z, ModBlocks.weepingPod.blockID, 3, 3);
				break;
			case 5:
				world.setBlock(x + 1, y, z, ModBlocks.weepingPod.blockID, 1, 3);
				break;
		}
	}
}