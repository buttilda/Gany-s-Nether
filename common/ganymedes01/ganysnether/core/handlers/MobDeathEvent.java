package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;

import java.util.Random;

import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class MobDeathEvent {

	@ForgeSubscribe
	public void modDeathEvent(LivingDeathEvent event) {
		Random rand = new Random();
		if (!event.entity.worldObj.isRemote)
			if (event.entity instanceof EntitySilverfish) {
				event.entity.dropItem(ModItems.silverfishScale.itemID, rand.nextInt(4));
				return;
			} else if (event.entity instanceof EntityBat) {
				event.entity.dropItem(ModItems.batWing.itemID, 1 + rand.nextInt(1));
				return;
			} else if (event.entity instanceof EntityWolf) {
				event.entity.dropItem(ModItems.wolfTeeth.itemID, rand.nextInt(2));
				return;
			}
	}

	@ForgeSubscribe
	public void playerDeathEvent(LivingDeathEvent event) {
		if (event.entityLiving.worldObj.isRemote)
			return;
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (!player.inventory.consumeInventoryItem(ModBlocks.undertaker.blockID))
				return;
			int x = (int) player.posX;
			int y = (int) player.posY;
			int z = (int) player.posZ;
			player.worldObj.setBlock(x, y, z, ModBlocks.undertaker.blockID);
			TileEntity tile = player.worldObj.getBlockTileEntity(x, y, z);
			if (tile instanceof TileEntityUndertaker) {
				TileEntityUndertaker undertaker = (TileEntityUndertaker) tile;
				for (int i = 0; i < player.inventory.mainInventory.length; i++)
					undertaker.setInventorySlotContents(i, player.inventory.mainInventory[i]);
				for (int i = 0; i < player.inventory.armorInventory.length; i++)
					undertaker.setInventorySlotContents(i + player.inventory.mainInventory.length, player.inventory.armorInventory[i]);
				event.setCanceled(true);
			}
		}
	}
}
