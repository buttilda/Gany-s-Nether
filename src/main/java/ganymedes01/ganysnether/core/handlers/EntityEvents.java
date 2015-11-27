package ganymedes01.ganysnether.core.handlers;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.integration.GravestoneModManager;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class EntityEvents {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void deathEvent(LivingDeathEvent event) {
		World world = event.entityLiving.worldObj;
		if (world.isRemote)
			return;

		if (event.entityLiving instanceof EntityPlayer && !world.getGameRules().getGameRuleBooleanValue("keepInventory")) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (!player.inventory.consumeInventoryItem(Item.getItemFromBlock(ModBlocks.undertaker)))
				return;

			int x = (int) player.posX;
			int y = (int) player.posY;
			int z = (int) player.posZ;
			if (!world.isAirBlock(x, y, z))
				y++;
			world.setBlock(x, y, z, ModBlocks.undertaker);
			TileEntityUndertaker undertaker = Utils.getTileEntity(world, x, y, z, TileEntityUndertaker.class);
			if (undertaker != null) {
				for (int i = 0; i < player.inventory.mainInventory.length; i++) {
					ItemStack cont = player.inventory.mainInventory[i];
					if (cont != null) {
						undertaker.setInventorySlotContents(i, cont.copy());
						player.inventory.mainInventory[i] = null;
					}
				}
				for (int i = 0; i < player.inventory.armorInventory.length; i++) {
					ItemStack cont = player.inventory.armorInventory[player.inventory.armorInventory.length - 1 - i];
					if (cont != null) {
						undertaker.setInventorySlotContents(i + player.inventory.mainInventory.length, cont.copy());
						player.inventory.armorInventory[player.inventory.armorInventory.length - 1 - i] = null;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void dropEvent(LivingDropsEvent event) {
		if (event.entityLiving.worldObj.isRemote)
			return;
		if (event.lootingLevel < 0)
			return;
		if (!GanysNether.enableReproducerAndDrops)
			return;

		Random rand = event.entityLiving.worldObj.rand;
		if (event.entityLiving instanceof EntitySilverfish)
			addDrop(new ItemStack(ModItems.silverfishScale, rand.nextInt(3 + event.lootingLevel)), event.entityLiving, event.drops);
		else if (event.entityLiving instanceof EntityBat)
			addDrop(new ItemStack(event.entityLiving.isBurning() ? ModItems.cookedBatWing : ModItems.batWing, 1 + (event.lootingLevel >= 2 ? 1 : rand.nextInt(1))), event.entityLiving, event.drops);
		else if (event.entityLiving instanceof EntityWolf || GravestoneModManager.isUndeadDog(event.entityLiving))
			addDrop(new ItemStack(ModItems.wolfTeeth, rand.nextInt(2 + event.lootingLevel)), event.entityLiving, event.drops);
	}

	private void addDrop(ItemStack stack, EntityLivingBase entity, List<EntityItem> list) {
		if (stack.stackSize <= 0)
			return;

		EntityItem entityItem = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, stack);
		entityItem.delayBeforeCanPickup = 10;
		list.add(entityItem);
	}
}