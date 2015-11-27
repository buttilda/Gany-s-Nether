package ganymedes01.ganysnether.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

@SideOnly(Side.CLIENT)
public class ModelBlazeArmour extends ModelBiped {

	private ModelRenderer[] blazeSticks = new ModelRenderer[12];

	public ModelBlazeArmour(float scale, boolean isTrousers) {
		super(scale);

		for (int i = 0; i < 4; ++i) {
			blazeSticks[i] = new ModelRenderer(this, 56, 16);
			blazeSticks[i].addBox(-1.0F, 0.0F, 0.0F, 2, 8, 2);
			bipedHead.addChild(blazeSticks[i]);
		}

		for (int i = 4; i < 8; ++i) {
			blazeSticks[i] = new ModelRenderer(this, 56, 16);
			blazeSticks[i].addBox(-1.0F, 2.0F, 0.0F, 2, 8, 2);
			if (!isTrousers)
				bipedBody.addChild(blazeSticks[i]);
		}

		for (int i = 8; i < 12; ++i) {
			blazeSticks[i] = new ModelRenderer(this, 56, 16);
			blazeSticks[i].addBox(-1.0F, 4.0F, 0.0F, 2, 8, 2);
			if (isTrousers)
				bipedBody.addChild(blazeSticks[i]);
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

		float f6 = par3 * (float) Math.PI * -0.1F;
		int i;

		for (i = 0; i < 4; ++i) {
			blazeSticks[i].rotationPointY = -2.0F + MathHelper.cos((i * 2 + par3) * 0.25F);
			blazeSticks[i].rotationPointX = MathHelper.cos(f6) * 9.0F;
			blazeSticks[i].rotationPointZ = MathHelper.sin(f6) * 9.0F;
			++f6;
		}

		f6 = (float) Math.PI / 4F + par3 * (float) Math.PI * 0.03F;

		for (i = 4; i < 8; ++i) {
			blazeSticks[i].rotationPointY = 2.0F + MathHelper.cos((i * 2 + par3) * 0.25F);
			blazeSticks[i].rotationPointX = MathHelper.cos(f6) * 7.0F;
			blazeSticks[i].rotationPointZ = MathHelper.sin(f6) * 7.0F;
			++f6;
		}

		f6 = 0.47123894F + par3 * (float) Math.PI * -0.05F;

		for (i = 8; i < 12; ++i) {
			blazeSticks[i].rotationPointY = 11.0F + MathHelper.cos((i * 1.5F + par3) * 0.5F);
			blazeSticks[i].rotationPointX = MathHelper.cos(f6) * 5.0F;
			blazeSticks[i].rotationPointZ = MathHelper.sin(f6) * 5.0F;
			++f6;
		}

		bipedHead.rotateAngleY = par4 / (180F / (float) Math.PI);
		bipedHead.rotateAngleX = par5 / (180F / (float) Math.PI);
	}
}
