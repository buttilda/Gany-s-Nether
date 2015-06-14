package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TooltipEvent {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void getTooltip(ItemTooltipEvent event) {
		if (event.itemStack == null)
			return;

		Item item = event.itemStack.getItem();

		if (item == Items.ghast_tear && GanysNether.shouldGenerateCrops && GanysNether.shouldGenerateWeepingPod)
			event.toolTip.add(StatCollector.translateToLocal("tooltip.ganysnether.ghastTear"));
		if (item == Item.getItemFromBlock(ModBlocks.extendedSpawner) && event.itemStack.hasTagCompound() && event.itemStack.getTagCompound().hasKey("EntityId"))
			event.toolTip.add(EnumChatFormatting.GOLD + event.itemStack.getTagCompound().getString("EntityId"));
	}
}