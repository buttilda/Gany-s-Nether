package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlazeChestplate extends BlazeArmour {

	public BlazeChestplate(int id) {
		super(id, 1);
		setTextureName(Utils.getItemTexture(Strings.BLAZE_CHESTPLATE_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.BLAZE_CHESTPLATE_NAME));
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {

	}
}
