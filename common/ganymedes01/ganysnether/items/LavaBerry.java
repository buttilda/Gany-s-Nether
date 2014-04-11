package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;

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

public class LavaBerry extends Item {

	public LavaBerry() {
		super(ModIDs.LAVA_BERRY_ID);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.Items.LAVA_BERRY_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.LAVA_BERRY_NAME));
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		list.add(StatCollector.translateToLocal("hotontheinside"));
	}
}