package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoulGlassPane extends BlockPane implements IConfigurable {

	public SoulGlassPane(int type) {
		super(Utils.getBlockTexture(Strings.Blocks.SOUL_GLASS_NAME + "_" + type), Utils.getBlockTexture(Strings.Blocks.SOUL_GLASS_NAME + "_" + type), Material.glass, false);
		setHardness(0.3F);
		setStepSound(soundTypeGlass);
		setCreativeTab(GanysNether.enableSoulGlass ? GanysNether.netherTab : null);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.SOUL_GLASS_PANE_NAME + type));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableSoulGlass;
	}
}