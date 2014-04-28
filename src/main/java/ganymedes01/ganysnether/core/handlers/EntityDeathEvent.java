package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;

import java.util.Random;

import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class EntityDeathEvent {

	@ForgeSubscribe(priority = EventPriority.HIGHEST)
	public void deathEvent(LivingDeathEvent event) {
		Random rand = new Random();
		if (!event.entityLiving.worldObj.isRemote)
			if (event.entityLiving instanceof EntitySilverfish) {
				event.entityLiving.dropItem(ModItems.silverfishScale.itemID, rand.nextInt(4));
				return;
			} else if (event.entityLiving instanceof EntityBat) {
				event.entityLiving.dropItem(ModItems.batWing.itemID, 1 + rand.nextInt(1));
				return;
			} else if (event.entityLiving instanceof EntityWolf) {
				event.entityLiving.dropItem(ModItems.wolfTeeth.itemID, rand.nextInt(2));
				return;
			} else if (event.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.entityLiving;
				if (!player.inventory.consumeInventoryItem(ModBlocks.undertaker.blockID))
					return;

				int x = (int) player.posX;
				int y = (int) player.posY;
				int z = (int) player.posZ;
				if (!player.worldObj.isAirBlock(x, y, z))
					y++;
				player.worldObj.setBlock(x, y, z, ModBlocks.undertaker.blockID);
				TileEntity tile = player.worldObj.getBlockTileEntity(x, y, z);
				if (tile instanceof TileEntityUndertaker) {
					TileEntityUndertaker undertaker = (TileEntityUndertaker) tile;
					for (int i = 0; i < player.inventory.mainInventory.length; i++) {
						ItemStack cont = player.inventory.mainInventory[i];
						if (cont != null)
							undertaker.setInventorySlotContents(i, cont.copy());
					}
					for (int i = 0; i < player.inventory.armorInventory.length; i++) {
						ItemStack cont = player.inventory.armorInventory[player.inventory.armorInventory.length - 1 - i];
						if (cont != null)
							undertaker.setInventorySlotContents(i + player.inventory.mainInventory.length, cont);
					}
					event.setCanceled(true);
				}
			}
	}
}