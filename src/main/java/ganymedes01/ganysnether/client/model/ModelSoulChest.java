package ganymedes01.ganysnether.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@SideOnly(Side.CLIENT)
public class ModelSoulChest extends ModelBase {

	private final ModelRenderer lidLeft, lidRight, knob;

	public ModelSoulChest() {
		textureHeight = 19;
		textureWidth = 42;

		lidRight = new ModelRenderer(this, 0, 0);
		lidRight.addBox(0F, -5F, -7F, 7, 5, 14);
		lidRight.setRotationPoint(-7F, 15F, 0F);

		lidLeft = new ModelRenderer(this, 0, 0);
		lidLeft.addBox(-7F, -5F, -7F, 7, 5, 14);
		lidLeft.setRotationPoint(7F, 15F, 0F);

		knob = new ModelRenderer(this, 0, 0);
		knob.addBox(6F, -6F, -2F, 2, 1, 4);
		knob.setRotationPoint(-7F, 15F, 0F);
	}

	public void setRotationAngles(float lidRotation) {
		lidLeft.rotateAngleZ = lidRotation * (float) Math.PI / 2.0F;
		knob.rotateAngleZ = lidRight.rotateAngleZ = -lidLeft.rotateAngleZ;
	}

	public void renderAll() {
		lidLeft.render(0.0625F);
		lidRight.render(0.0625F);
		knob.render(0.0625F);
	}
}