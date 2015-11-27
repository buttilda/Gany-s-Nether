package ganymedes01.ganysnether.items;

import java.util.List;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class LavaBerry extends Item implements IConfigurable {

	public LavaBerry() {
		setTextureName(Utils.getItemTexture(Strings.Items.LAVA_BERRY_NAME));
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.LAVA_BERRY_NAME));
		setCreativeTab(GanysNether.shouldGenerateCrops ? GanysNether.netherTab : null);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		list.add(StatCollector.translateToLocal("tooltip.ganysnether.hotInside"));
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.shouldGenerateCrops && GanysNether.shouldGenerateHellBush;
	}
}