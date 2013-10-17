package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.blocks.NetherCrop;
import ganymedes01.ganysnether.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class BonemealOnNetherCrops {

	@ForgeSubscribe
	public void bonemealEvent(BonemealEvent event) {
		ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
		int meta = event.world.getBlockMetadata(event.X, event.Y, event.Z);

		if (stack != null && stack.getItem() == ModItems.livingSoul)
			if (Block.blocksList[event.ID] instanceof NetherCrop) {
				if (meta < 7) {
					meta += 2;
					if (meta > 7)
						meta = 7;
					event.world.setBlockMetadataWithNotify(event.X, event.Y, event.Z, meta, 3);
					event.setResult(Result.ALLOW);
					return;
				}
			} else if (event.ID == Block.netherStalk.blockID)
				if (meta < 3) {
					event.world.setBlockMetadataWithNotify(event.X, event.Y, event.Z, ++meta, 3);
					event.setResult(Result.ALLOW);
					return;
				}
	}
}