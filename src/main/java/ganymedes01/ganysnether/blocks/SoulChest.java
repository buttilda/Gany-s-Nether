package ganymedes01.ganysnether.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.InventoryUtils;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class SoulChest extends InventoryBlock implements IConfigurable {

	@SideOnly(Side.CLIENT)
	protected IIcon sides, bottom;

	public SoulChest() {
		super(Material.sand);
		setHardness(2.5F);
		setStepSound(soundTypeSand);
		setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.SOUL_CHEST_NAME));
		setCreativeTab(GanysNether.enableSoulChest ? GanysNether.netherTab : null);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityItem && !world.isRemote) {
			TileEntitySoulChest tile = Utils.getTileEntity(world, x, y, z, TileEntitySoulChest.class);
			if (tile != null)
				InventoryUtils.addEntitytoInventory(tile, (EntityItem) entity);
		} else {
			entity.motionX *= 0.4D;
			entity.motionZ *= 0.4D;
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
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.SOUL_CHEST_NAME + "_top"));
		sides = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.SOUL_CHEST_NAME + "_side"));
		bottom = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.SOUL_CHEST_NAME + "_bottom"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? blockIcon : side == 0 ? bottom : sides;
	}

	@Override
	public int getRenderType() {
		return RenderIDs.SOUL_CHEST;
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		IInventory iinventory = Utils.getTileEntity(world, x, y, z, IInventory.class);
		if (iinventory != null)
			if (!InventoryUtils.addStackToInventory(iinventory, player.inventory.getCurrentItem())) {
				player.displayGUIChest(iinventory);
				return true;
			}

		return !player.isSneaking();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySoulChest();
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableSoulChest;
	}
}