package ganymedes01.ganysnether.client.renderer.block;

import ganymedes01.ganysnether.core.utils.Utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Based on RWTema's implementation for his mod DenseOres.
 *
 * @link https://github.com/rwtema/DenseOres
 */
@SideOnly(Side.CLIENT)
public class IconSpectreWheat extends TextureAtlasSprite {

	private final Block base;
	private final int meta;

	private IconSpectreWheat(Block base, int meta, String name) {
		super(Utils.getBlockTexture(name));
		this.base = base;
		this.meta = meta;
	}

	public static IIcon getIcon(TextureMap map, Block base, int meta, String name) {
		IconSpectreWheat icon = new IconSpectreWheat(base, meta, name);
		map.setTextureEntry(icon.getIconName(), icon);
		return icon;
	}

	@Override
	public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
		try {
			manager.getResource(location);
		} catch (IOException e) {
			return true;
		}
		return false;
	}

	@Override
	public boolean load(IResourceManager manager, ResourceLocation location) {
		try {
			IResource iresource = manager.getResource(getBlockResource(base, meta));
			AnimationMetadataSection meta = (AnimationMetadataSection) iresource.getMetadata("animation");
			BufferedImage icon = ImageIO.read(iresource.getInputStream());
			BufferedImage inverted = new BufferedImage(icon.getWidth(), icon.getHeight(), icon.getType());

			for (int x = 0; x < icon.getWidth(); x++)
				for (int y = 0; y < icon.getHeight(); y++) {
					int pixel = icon.getRGB(x, y);
					int red = 255 - ((pixel & 0x00ff0000) >> 16);
					int green = 255 - ((pixel & 0x0000ff00) >> 8);
					int blue = 255 - (pixel & 0x000000ff);
					int alpha = (pixel & 0xff000000) == 0 ? 0 : 150;

					inverted.setRGB(x, y, new Color(red, green, blue, alpha).getRGB());
				}

			BufferedImage[] image = new BufferedImage[Minecraft.getMinecraft().gameSettings.mipmapLevels + 1];
			image[0] = inverted;
			loadSprite(image, meta, Minecraft.getMinecraft().gameSettings.anisotropicFiltering > 1.0F);
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

		return false;
	}

	public static ResourceLocation getBlockResource(Block block, int meta) {
		return getIconResource(block.getIcon(0, meta));
	}

	public static ResourceLocation getBlockResource(Block block) {
		return getBlockResource(block, 0);
	}

	public static ResourceLocation getIconResource(IIcon icon) {
		String iconName = icon.getIconName();
		String string = "minecraft";

		int colonIndex = iconName.indexOf(58);
		if (colonIndex >= 0) {
			if (colonIndex > 1)
				string = iconName.substring(0, colonIndex);

			iconName = iconName.substring(colonIndex + 1, iconName.length());
		}

		string = string.toLowerCase();
		iconName = "textures/blocks/" + iconName + ".png";
		return new ResourceLocation(string, iconName);
	}
}