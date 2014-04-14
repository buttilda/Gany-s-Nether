package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityExtendedSpawner;

import java.awt.Color;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SpawnerUpgrade extends Item {

	public enum UpgradeType {
		// @formatter:off
		tierCoal(Utils.getColour(33, 40, 45), new ItemStack(Item.coal), new ItemStack(Block.coalBlock)),
		tierIron(Utils.getColour(255, 255, 255), new ItemStack(Item.ingotIron), new ItemStack(Block.blockIron)),
		tierLapis(Utils.getColour(29, 47, 157), new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Block.blockLapis)),
		tierGold(Utils.getColour(255, 204, 53), new ItemStack(Item.ingotGold), new ItemStack(Block.blockGold)),
		tierDiamond(Utils.getColour(30, 207, 199), new ItemStack(Item.diamond), new ItemStack(Block.blockDiamond)),
		tierEmerald(Utils.getColour(63, 213, 102), new ItemStack(Item.emerald), new ItemStack(Block.blockEmerald)),
		tierNetherStar(Utils.getColour(136, 164, 164), new ItemStack(Item.ghastTear), new ItemStack(Item.netherStar)),
		tierDragonEgg(Utils.getColour(87, 2, 98), null, null),
		redstone(Utils.getColour(181, 31, 10), new ItemStack(Item.redstone), new ItemStack(Block.blockRedstone)),
		noPlayer(Utils.getColour(255, 162, 3), new ItemStack(Item.appleRed), new ItemStack(Item.appleGold, 1, 1)),
		ignoreConditions(0, new ItemStack(ModItems.sceptreCap), new ItemStack(ModBlocks.denseLavaCell)),
		silky(0, new ItemStack(Block.oreNetherQuartz), Utils.enchantStack(new ItemStack(Item.enchantedBook), Enchantment.silkTouch, 1)),
		spawnCount(0, null, null),
		spawnRange(0, new ItemStack(Block.fire), new ItemStack(Block.blockNetherQuartz));
		// @formatter:on

		private final int colour;
		private final ItemStack mat1, mat2;

		UpgradeType(int colour, ItemStack mat1, ItemStack mat2) {
			this.colour = colour;
			this.mat1 = mat1;
			this.mat2 = mat2;
		}

		public int getColour() {
			return colour == 0 ? new Color(name().hashCode()).getRGB() : colour;
		}

		public ItemStack getMat1() {
			return mat1 == null ? null : mat1.copy();
		}

		public ItemStack getMat2() {
			return mat2 == null ? null : mat2.copy();
		}
	}

	@SideOnly(Side.CLIENT)
	private Icon inside;

	public SpawnerUpgrade() {
		super(ModIDs.SPAWNER_UPGRADE_ID);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.Items.SPAWNER_UPGRADE_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.SPAWNER_UPGRADE_NAME));
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote || player.isSneaking())
			return true;
		int id = world.getBlockId(x, y, z);
		int meta = stack.getItemDamage();

		if (id == Block.mobSpawner.blockID) {
			if (meta != UpgradeType.tierCoal.ordinal())
				return false;
			NBTTagCompound data = new NBTTagCompound();
			TileEntityMobSpawner old = (TileEntityMobSpawner) world.getBlockTileEntity(x, y, z);
			old.writeToNBT(data);
			if (old.getSpawnerLogic().getEntityNameToSpawn().equalsIgnoreCase("skeleton"))
				data.setBoolean("isWitherSkeleton", world.provider.isHellWorld);
			world.setBlock(x, y, z, ModBlocks.extendedSpawner.blockID);
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			tile.readFromNBT(data);
			PacketDispatcher.sendPacketToAllPlayers(tile.getDescriptionPacket());
			useItem(stack, player);
			return true;
		}

		boolean used = false;
		if (id == ModBlocks.extendedSpawner.blockID) {
			if (meta == UpgradeType.tierCoal.ordinal())
				return false;
			TileEntityExtendedSpawner tile = (TileEntityExtendedSpawner) world.getBlockTileEntity(x, y, z);
			if (meta <= UpgradeType.tierDragonEgg.ordinal() && tile.logic.tier + 1 == meta) {
				tile.logic.tier = (byte) meta;
				used = true;
			}
			if (tile.getSlots() - tile.getSlotsUsed() > 0)
				if (meta == UpgradeType.redstone.ordinal() && !tile.logic.redstoneUpgrade) {
					tile.logic.redstoneUpgrade = true;
					used = true;
				} else if (meta == UpgradeType.noPlayer.ordinal() && !tile.logic.noPlayerUpgrade) {
					tile.logic.noPlayerUpgrade = true;
					used = true;
				} else if (meta == UpgradeType.ignoreConditions.ordinal() && !tile.logic.ignoreConditionsUpgrade) {
					tile.logic.ignoreConditionsUpgrade = true;
					used = true;
				} else if (meta == UpgradeType.silky.ordinal() && !tile.logic.silkyUpgrade) {
					tile.logic.silkyUpgrade = true;
					world.setBlockMetadataWithNotify(x, y, z, 1, 3);
					used = true;
				} else if (meta == UpgradeType.spawnCount.ordinal()) {
					tile.logic.spawnCount++;
					tile.logic.maxNearbyEntities++;
					used = true;
				} else if (meta == UpgradeType.spawnRange.ordinal()) {
					tile.logic.spawnRange++;
					tile.logic.maxNearbyEntities += 2;
					used = true;
				}

			if (used) {
				useItem(stack, player);
				PacketDispatcher.sendPacketToAllPlayers(tile.getDescriptionPacket());
				return true;
			}
		}

		return false;
	}

	private void useItem(ItemStack stack, EntityPlayer player) {
		if (player.capabilities.isCreativeMode)
			return;
		stack.stackSize--;
		if (stack.stackSize <= 0)
			stack = null;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + stack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		return pass == 1 ? UpgradeType.values()[stack.getItemDamage()].getColour() : Utils.getColour(255, 255, 255);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamageForRenderPass(int meta, int pass) {
		return pass == 1 ? inside : super.getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		super.registerIcons(reg);
		inside = reg.registerIcon(getIconString() + "_inside");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tabs, List list) {
		for (int i = 0; i < UpgradeType.values().length; i++)
			list.add(new ItemStack(itemID, 1, i));
	}
}