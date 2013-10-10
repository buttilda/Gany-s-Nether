package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class BonemealOnWitherShrubEvent {

	@ForgeSubscribe
	public void endBonemealEvent(BonemealEvent event) {
		if (event.ID == ModBlocks.witherShrub.blockID) {
			int meta = event.world.getBlockMetadata(event.X, event.Y, event.Z);
			if (meta < 7) {
				event.world.setBlockMetadataWithNotify(event.X, event.Y, event.Z, ++meta, 2);
				event.setResult(Result.ALLOW);
			}
		}
	}
}
