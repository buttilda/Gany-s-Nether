package ganymedes01.ganysnether.items;

import java.util.List;

import cpw.mods.fml.common.eventhandler.Event.Result;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class LivingSoul extends Item implements IConfigurable {

	public LivingSoul() {
		setFull3D();
		setTextureName(Utils.getItemTexture(Strings.Items.LIVING_SOUL_NAME));
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.LIVING_SOUL_NAME));
		setCreativeTab(GanysNether.enableLivingSoul ? GanysNether.netherTab : null);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		list.add(StatCollector.translateToLocal("tooltip." + Reference.MOD_ID + ".boo"));
	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (applyBonemeal(item, world, x, y, z, player)) {
			if (!world.isRemote)
				world.playAuxSFX(2005, x, y, z, 0);
			return true;
		}
		return false;
	}

	public static boolean applyBonemeal(ItemStack stack, World world, int x, int y, int z, EntityPlayer player) {
		BonemealEvent event = new BonemealEvent(player, world, world.getBlock(x, y, z), x, y, z);
		if (MinecraftForge.EVENT_BUS.post(event))
			return false;

		if (event.getResult() == Result.ALLOW) {
			if (!world.isRemote)
				stack.stackSize--;
			return true;
		}
		return false;
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableLivingSoul;
	}
}