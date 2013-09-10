package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlazeBoots extends BlazeArmour {

	public BlazeBoots(int id) {
		super(id, 3);
		setTextureName(Utils.getItemTexture(Strings.BLAZE_BOOTS_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.BLAZE_BOOTS_NAME));
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {

	}
}
