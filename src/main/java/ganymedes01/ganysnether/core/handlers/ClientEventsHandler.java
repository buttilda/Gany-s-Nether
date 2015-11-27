package ganymedes01.ganysnether.core.handlers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.SpectreWheatCrop;
import ganymedes01.ganysnether.client.renderer.block.IconSpectreWheat;
import ganymedes01.ganysnether.client.renderer.item.IconInvertedTransparent;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.DimensionalBread;
import ganymedes01.ganysnether.items.GhostSeeds;
import ganymedes01.ganysnether.items.SpectreWheat;
import ganymedes01.ganysnether.items.WitherShrubSeeds;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@SideOnly(Side.CLIENT)
public class ClientEventsHandler {

	private static final ArrayList<String> usersWithCapes = new ArrayList<String>();
	private static ResourceLocation CAPE_DATA = Utils.getResource(Reference.MOD_ID + ":textures/capes/gany.png");
	private static ResourceLocation JEBJEB_CAPE_DATA = Utils.getResource(Reference.MOD_ID + ":textures/capes/jade.png");
	private static ResourceLocation KPR_CAPE_DATA = Utils.getResource(Reference.MOD_ID + ":textures/capes/KingPurpleRaptor.png");

	public ClientEventsHandler() {
		try {
			Scanner scanner = new Scanner(new URL(Reference.USERS_WITH_CAPES_FILE).openStream());
			while (scanner.hasNext())
				usersWithCapes.add(scanner.nextLine());
			scanner.close();
		} catch (IOException e) {
		}

		makeImage(CAPE_DATA, "gany");
		makeImage(JEBJEB_CAPE_DATA, "jade");
		makeImage(KPR_CAPE_DATA, "KingPurpleRaptor");
	}

	private void makeImage(ResourceLocation resource, String name) {
		try {
			InputStream is = GanysNether.class.getResourceAsStream("/assets/" + Reference.MOD_ID + "/capes/" + name + ".cape");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			List<Color> colours = new ArrayList<Color>();
			while ((line = br.readLine()) != null)
				colours.add(decode(line.trim()));
			br.close();

			BufferedImage image = new BufferedImage(64, 32, BufferedImage.TYPE_INT_ARGB);

			int x = 0;
			int y = 0;
			for (Color colour : colours) {
				image.setRGB(x, y, colour.getRGB());
				x++;
				if (x >= image.getWidth()) {
					x = 0;
					y++;
				}
			}

			Minecraft.getMinecraft().getTextureManager().loadTexture(resource, new DynamicTexture(image));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Color decode(String nm) throws NumberFormatException {
		if (nm.startsWith("0x"))
			nm = nm.substring(2);
		Long val = Long.parseLong(nm, 16);
		long l = val.longValue();
		int r = (int) (l >> 16 & 0xFF);
		int g = (int) (l >> 8 & 0xFF);
		int b = (int) (l & 0xFF);
		int a = (int) (l >> 24 & 0xFF);

		return new Color(r, g, b, a);
	}

	@SubscribeEvent
	public void onPreRenderSpecials(RenderPlayerEvent.Specials.Pre event) {
		if (!(event.entityPlayer instanceof AbstractClientPlayer))
			return;
		String name = event.entityPlayer.getCommandSenderName();
		if (usersWithCapes.contains(name)) {
			AbstractClientPlayer player = (AbstractClientPlayer) event.entityPlayer;

			if (event.entityPlayer.getCommandSenderName().equals("Jeb_Jeb"))
				setCape(player, JEBJEB_CAPE_DATA);
			else if (event.entityPlayer.getCommandSenderName().equals("KingPurpleRaptor"))
				setCape(player, KPR_CAPE_DATA);
			else
				setCape(player, CAPE_DATA);

			event.renderCape = true;
		}
	}

	private void setCape(AbstractClientPlayer player, ResourceLocation resource) {
		if (player.getLocationCape() == null || !player.getLocationCape().equals(resource))
			player.func_152121_a(MinecraftProfileTexture.Type.CAPE, resource);
	}

	@SubscribeEvent
	public void loadTextures(TextureStitchEvent.Pre event) {
		if (!GanysNether.enableDynamicTextures)
			return;

		if (event.map.getTextureType() == 0) {
			SpectreWheatCrop.icons = new IIcon[8];

			for (int i = 0; i < 8; i++)
				SpectreWheatCrop.icons[i] = IconSpectreWheat.getIcon(event.map, Blocks.wheat, i, Strings.Blocks.SPECTRE_WHEAT_BLOCK_NAME + "_stage_" + i);
		}
		if (event.map.getTextureType() == 1) {
			SpectreWheat.setIcon(IconInvertedTransparent.getIcon(event.map, Items.wheat, 0, Strings.Items.SPECTRE_WHEAT_NAME));
			GhostSeeds.setIcon(IconInvertedTransparent.getIcon(event.map, Items.wheat_seeds, 0, Strings.Items.GHOST_SEEDS_NAME));
			DimensionalBread.setIcon(IconInvertedTransparent.getIcon(event.map, Items.bread, 0, Strings.Items.DIMENSIONAL_BREAD_NAME));
			WitherShrubSeeds.setIcon(IconInvertedTransparent.getIcon(event.map, Items.pumpkin_seeds, 0, Strings.Items.WITHER_SHRUB_SEEDS_NAME));
		}
	}
}