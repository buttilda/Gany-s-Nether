package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.UseHoeEvent;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class HoeEvent {

	@ForgeSubscribe
	public void onHoeUseEvent(UseHoeEvent event) {
		if (event.world.getBlockId(event.x, event.y, event.z) == Block.netherrack.blockID)
			if (event.current.getItem() instanceof ItemHoe) {
				ItemHoe item = (ItemHoe) event.current.getItem();
				if (item.getMaterialName() == "EMERALD" || item.getMaterialName() == "GOLD" || item.getMaterialName() == "THAUMIUM") {
					event.world.setBlock(event.x, event.y, event.z, ModBlocks.tilledNetherrack.blockID);
					event.setResult(Result.ALLOW);
				}
			}
	}
}
