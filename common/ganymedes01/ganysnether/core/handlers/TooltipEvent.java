package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class TooltipEvent {

	@ForgeSubscribe(priority = EventPriority.HIGHEST)
	public void getTooltip(ItemTooltipEvent event) {
		if (event.itemStack == null)
			return;

		int id = event.itemStack.itemID;

		if (id == Item.ghastTear.itemID)
			event.toolTip.add(StatCollector.translateToLocal("tooltip.ganysnether.ghastTear"));
		if (id == ModBlocks.extendedSpawner.blockID && event.itemStack.hasTagCompound() && event.itemStack.getTagCompound().hasKey("EntityId"))
			event.toolTip.add(EnumChatFormatting.GOLD + event.itemStack.getTagCompound().getString("EntityId"));
	}
}