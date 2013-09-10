package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SoulChest extends BlockContainer {
	private final Random random = new Random();

	protected SoulChest(int id) {
		super(id, Material.sand);
		setHardness(2.5F);
		setStepSound(soundSandFootstep);
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SOUL_CHEST_NAME));
		setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityItem && !world.isRemote) {
			TileEntitySoulChest tile = (TileEntitySoulChest) world.getBlockTileEntity(x, y, z);
			addEntitytoInventory(tile, (EntityItem) entity);
		} else {
			entity.motionX *= 0.4D;
			entity.motionZ *= 0.4D;
		}
	}

	private void addEntitytoInventory(IInventory iinventory, EntityItem item) {
		ArrayList<Integer> slots = getStackSlots(iinventory, item.getEntityItem());

		while (slots.size() > 0 && item.getEntityItem().stackSize > 0) {
			for (Integer slot : slots)
				while (iinventory.getStackInSlot(slot).stackSize < iinventory.getStackInSlot(slot).getMaxStackSize() && item.getEntityItem().stackSize > 0) {
					++iinventory.getStackInSlot(slot).stackSize;
					--item.getEntityItem().stackSize;
				}
			slots = getStackSlots(iinventory, item.getEntityItem());
		}
		if (item.getEntityItem().stackSize <= 0) {
			item.setDead();
			return;
		}

		for (int i = 0; i < iinventory.getSizeInventory(); i++)
			if (iinventory.getStackInSlot(i) == null) {
				iinventory.setInventorySlotContents(i, item.getEntityItem());
				item.setDead();
				return;
			}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 22;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		byte b0 = 0;
		int l1 = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l1 == 0)
			b0 = 2;
		if (l1 == 1)
			b0 = 5;
		if (l1 == 2)
			b0 = 3;
		if (l1 == 3)
			b0 = 4;

		world.setBlockMetadataWithNotify(x, y, z, b0, 3);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		TileEntitySoulChest tile = (TileEntitySoulChest) world.getBlockTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); ++i) {
				ItemStack stack = tile.getStackInSlot(i);
				if (stack != null)
					Utils.dropStack(world, x, y, z, stack);
			}
			world.func_96440_m(x, y, z, par5);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		IInventory iinventory = getInventory(world, x, y, z);
		if (player.inventory.getCurrentItem() == null) {
			if (iinventory != null)
				player.displayGUIChest(iinventory);
			return true;
		} else
			addStacktoInventory(iinventory, player);
		return true;
	}

	private void addStacktoInventory(IInventory iinventory, EntityPlayer player) {
		ArrayList<Integer> slots = getStackSlots(iinventory, player.inventory.getCurrentItem());

		while (slots.size() > 0 && player.inventory.getCurrentItem().stackSize > 0) {
			for (Integer slot : slots)
				while (iinventory.getStackInSlot(slot).stackSize < iinventory.getStackInSlot(slot).getMaxStackSize() && player.inventory.getCurrentItem().stackSize > 0) {
					++iinventory.getStackInSlot(slot).stackSize;
					--player.inventory.getCurrentItem().stackSize;
				}
			slots = getStackSlots(iinventory, player.inventory.getCurrentItem());
		}
		if (player.inventory.getCurrentItem().stackSize <= 0)
			return;

		for (int i = 0; i < iinventory.getSizeInventory(); i++)
			if (iinventory.getStackInSlot(i) == null) {
				iinventory.setInventorySlotContents(i, player.inventory.getCurrentItem());
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				return;
			}
	}

	private static ArrayList<Integer> getStackSlots(IInventory iinventory, ItemStack stack) {
		ArrayList<Integer> slots = new ArrayList<Integer>();
		if (stack.stackSize > 0)
			for (int i = 0; i < iinventory.getSizeInventory(); i++)
				if (iinventory.getStackInSlot(i) != null && stack != null)
					if (iinventory.getStackInSlot(i).getItem() == stack.getItem())
						if (iinventory.getStackInSlot(i).isItemEqual(stack))
							if (iinventory.getStackInSlot(i).stackSize < iinventory.getStackInSlot(i).getMaxStackSize())
								slots.add(i);
		return slots;
	}

	public IInventory getInventory(World world, int x, int y, int z) {
		Object object = world.getBlockTileEntity(x, y, z);

		if (object == null)
			return null;
		else if (isOcelotBlockingChest(world, x, y, z))
			return null;

		return (IInventory) object;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySoulChest();
	}

	public static boolean isOcelotBlockingChest(World world, int x, int y, int z) {
		Iterator iterator = world.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB(x, y + 1, z, x + 1, y + 2, z + 1)).iterator();
		EntityOcelot entityocelot;

		do {
			if (!iterator.hasNext())
				return false;

			EntityOcelot entityocelot1 = (EntityOcelot) iterator.next();
			entityocelot = entityocelot1;
		} while (!entityocelot.isSitting());

		return true;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int par5) {
		return Container.calcRedstoneFromInventory(getInventory(world, x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("soul_sand");
	}
}
