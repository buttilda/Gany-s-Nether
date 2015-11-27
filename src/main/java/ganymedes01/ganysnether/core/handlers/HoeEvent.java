package ganymedes01.ganysnether.core.handlers;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.core.utils.HoeList;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.UseHoeEvent;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class HoeEvent {

	@SubscribeEvent
	public void onHoeUseEvent(UseHoeEvent event) {
		if (GanysNether.shouldGenerateCrops && event.world.getBlock(event.x, event.y, event.z) == Blocks.netherrack)
			if (event.current != null)
				if (HoeList.canTillNetherrack(event.current.getItem())) {
					event.world.setBlock(event.x, event.y, event.z, ModBlocks.tilledNetherrack);
					event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, Block.soundTypeGravel.getStepResourcePath(), 1.0F, 0.8F);
					event.setResult(Result.ALLOW);
				}
	}
}