package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.blocks.NetherCrop;
import ganymedes01.ganysnether.items.ModItems;
import net.minecraft.block.BlockDirectional;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class BonemealOnNetherCrops {

	@SubscribeEvent
	public void bonemealEvent(BonemealEvent event) {
		if (event.entityPlayer == null)
			return;
		ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
		int meta = event.world.getBlockMetadata(event.x, event.y, event.z);

		if (stack != null)
			if (stack.getItem() == ModItems.livingSoul) {
				if (event.block instanceof NetherCrop) {
					if (meta < 7) {
						event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, ++meta, 3);
						event.entityPlayer.swingItem();
						event.setResult(Result.ALLOW);
						return;
					}
				} else if (event.block == Blocks.nether_wart) {
					if (meta < 3) {
						event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, ++meta, 3);
						event.entityPlayer.swingItem();
						event.setResult(Result.ALLOW);
						return;
					}
				} else if (event.block == ModBlocks.weepingPod) {
					int stage = (meta & 12) >> 2;

					if (stage < 2) {
						event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, ++stage << 2 | BlockDirectional.getDirection(meta), 2);
						event.entityPlayer.swingItem();
						event.setResult(Result.ALLOW);
						return;
					}
				}
			} else if (event.block instanceof NetherCrop || event.block == ModBlocks.weepingPod) {
				event.setCanceled(true);
				return;
			}
	}
}