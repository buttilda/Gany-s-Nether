package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.HoeList;
import net.minecraft.block.Block;
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
			if (event.current != null)
				if (HoeList.canTillNetherrack(event.current.getItem())) {
					event.world.setBlock(event.x, event.y, event.z, ModBlocks.tilledNetherrack.blockID);
					event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, Block.soundGravelFootstep.getStepSound(), 1.0F, 0.8F);
					event.setResult(Result.ALLOW);
				}
	}
}