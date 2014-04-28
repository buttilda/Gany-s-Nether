package ganymedes01.ganysnether.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

@SideOnly(Side.CLIENT)
public class ModelMagmaticCentrifuge extends ModelBase {

	private final ModelRenderer bar1, bar2, bar3, bar4, base, top, core;

	public ModelMagmaticCentrifuge() {
		textureHeight = 49;

		bar1 = new ModelRenderer(this, 0, 0);
		bar1.addBox(7F, 1F, 7F, 1, 14, 1);
		bar2 = new ModelRenderer(this, 0, 0);
		bar2.addBox(7F, 1F, -8F, 1, 14, 1);
		bar3 = new ModelRenderer(this, 0, 0);
		bar3.addBox(-8F, 1F, 7F, 1, 14, 1);
		bar4 = new ModelRenderer(this, 0, 0);
		bar4.addBox(-8F, 1F, -8F, 1, 14, 1);

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-8F, 15F, -8F, 16, 1, 16);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-8F, 0F, -8F, 16, 1, 16);

		ModelRenderer coreBase = new ModelRenderer(this, 0, 0).setTextureOffset(0, 17);
		coreBase.addBox(-6F, 11.95F, -6F, 12, 4, 12);
		ModelRenderer coreBaseBase = new ModelRenderer(this, 0, 0).setTextureOffset(0, 33);
		coreBaseBase.addBox(-7F, 13.95F, -7F, 14, 2, 14);
		base.addChild(coreBase);
		base.addChild(coreBaseBase);

		ModelRenderer coreTop = new ModelRenderer(this, 0, 0).setTextureOffset(0, 17);
		coreTop.addBox(-6F, 0.05F, -6F, 12, 4, 12);
		ModelRenderer coreTopBase = new ModelRenderer(this, 0, 0).setTextureOffset(0, 33);
		coreTopBase.addBox(-7F, 0.05F, -7F, 14, 2, 14);
		top.addChild(coreTop);
		top.addChild(coreTopBase);

		core = new ModelRenderer(this, 0, 0).setTextureOffset(48, 17);
		core.addBox(-2.0F, 1.0F, -7.0F, 4, 14, 4, 0.05F);
		ModelRenderer core2 = new ModelRenderer(this, 0, 0).setTextureOffset(48, 17);
		core2.addBox(-2.0F, 1.0F, 3.0F, 4, 14, 4, 0.05F);
		core.addChild(core2);
	}

	public void setCoreAngle(float angle) {
		core.rotateAngleY = (float) (-angle * (Math.PI / 180));
	}

	public void renderAll() {
		bar1.render(0.0625F);
		bar2.render(0.0625F);
		bar4.render(0.0625F);
		bar3.render(0.0625F);
		base.render(0.0625F);
		top.render(0.0625F);
		core.render(0.0625F);
	}
}