package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.entities.EntitySlowTNT;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Icon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SoulTNT extends BlockTNT {

	@SideOnly(Side.CLIENT)
	private Icon blockTop, blockBottom;

	public SoulTNT() {
		super(ModIDs.SOUL_TNT_ID);
		setHardness(0.0F);
		setStepSound(soundGrassFootstep);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getBlockTexture(Strings.SOUL_TNT_NAME) + "_");
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SOUL_TNT_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? blockBottom : side == 1 ? blockTop : blockIcon;
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
		if (!world.isRemote) {
			EntitySlowTNT tnt = new EntitySlowTNT(world, x + 0.5F, y + 0.5F, z + 0.5F, explosion.getExplosivePlacedBy());
			tnt.fuse = world.rand.nextInt(tnt.fuse / 4) + tnt.fuse / 8;
			world.spawnEntityInWorld(tnt);
		}
	}

	@Override
	public void primeTnt(World world, int x, int y, int z, int meta, EntityLivingBase entity) {
		if (!world.isRemote)
			if ((meta & 1) == 1) {
				EntitySlowTNT tnt = new EntitySlowTNT(world, x + 0.5F, y + 0.5F, z + 0.5F, entity);
				world.spawnEntityInWorld(tnt);
				world.playSoundAtEntity(tnt, "random.fuse", 1.0F, 1.0F);
			}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon(getTextureName() + "side");
		blockTop = reg.registerIcon(getTextureName() + "top");
		blockBottom = reg.registerIcon(getTextureName() + "bottom");
	}
}