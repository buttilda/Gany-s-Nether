package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class WeepingPod extends BlockCocoa {

	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public WeepingPod() {
		super(ModIDs.WEEPING_POD_ID);
		setHardness(0.2F);
		setResistance(5.0F);
		setStepSound(soundWoodFootstep);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.WEEPING_POD_NAME));
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> dropped = new ArrayList<ItemStack>();
		dropped.add(new ItemStack(Item.ghastTear));

		if ((meta & 12) >> 2 >= 2)
			dropped.add(new ItemStack(Item.ghastTear));

		return dropped;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		int direction = getDirection(world.getBlockMetadata(x, y, z));
		x += Direction.offsetX[direction];
		z += Direction.offsetZ[direction];
		int blockID = world.getBlockId(x, y, z);
		return blockID == Block.obsidian.blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		iconArray = new Icon[3];
		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = reg.registerIcon(Utils.getBlockTexture(Strings.WEEPING_POD_NAME, true) + "stage_" + i);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getCocoaIcon(int meta) {
		if (meta < 0 || meta >= iconArray.length)
			meta = iconArray.length - 1;

		return iconArray[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return iconArray[2];
	}
}