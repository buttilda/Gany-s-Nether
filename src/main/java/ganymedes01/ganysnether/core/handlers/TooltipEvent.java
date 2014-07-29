package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.items.SpawnerUpgrade.UpgradeType;
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

		if (item == Items.ghast_tear)
			event.toolTip.add(StatCollector.translateToLocal("tooltip.ganysnether.ghastTear"));
		if (item == Item.getItemFromBlock(ModBlocks.extendedSpawner) && event.itemStack.hasTagCompound() && event.itemStack.getTagCompound().hasKey("EntityId"))
			event.toolTip.add(EnumChatFormatting.GOLD + event.itemStack.getTagCompound().getString("EntityId"));
		else if (item == ModItems.spawnerUpgrade) {
			int meta = event.itemStack.getItemDamage();
			if (meta == UpgradeType.tierCoal.ordinal())
				event.toolTip.add("First Upgrade");
			else if (meta < UpgradeType.tierDragonEgg.ordinal())
				event.toolTip.add("+2 Slots");
			else if (meta == UpgradeType.tierDragonEgg.ordinal()) {
				event.toolTip.add("+2 Slots");
				event.toolTip.add("Spawns up to 3 different mobs");
			} else if (meta == UpgradeType.redstone.ordinal())
				event.toolTip.add("Control spawner with redstone signals");
			else if (meta == UpgradeType.noPlayer.ordinal())
				event.toolTip.add("Spawner will run even if there's no players nearby");
			else if (meta == UpgradeType.ignoreConditions.ordinal())
				event.toolTip.add("Spawner will ignore light levels, dimension, etc...");
			else if (meta == UpgradeType.silky.ordinal())
				event.toolTip.add("Spawner block will drop when broken");
			else if (meta == UpgradeType.spawnCount.ordinal())
				event.toolTip.add("+1 possible spawn");
			else if (meta == UpgradeType.spawnRange.ordinal())
				event.toolTip.add("+2,+0,+2 (x,y,z) spawn range upgrade");
		}
	}
}