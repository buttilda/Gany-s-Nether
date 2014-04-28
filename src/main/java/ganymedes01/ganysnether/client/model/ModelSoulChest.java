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
public class ModelSoulChest extends ModelBase {

	private final ModelRenderer chestLidLeft, chestLidRight, chestBottom, chestKnob;

	public ModelSoulChest() {
		textureHeight = 64;

		chestLidRight = new ModelRenderer(this, 0, 0);
		chestLidRight.addBox(0F, -5F, -7F, 7, 5, 14, 0.05F);
		chestLidRight.setRotationPoint(-7F, 15F, 0F);

		chestLidLeft = new ModelRenderer(this, 0, 45);
		chestLidLeft.addBox(-7F, -5F, -7F, 7, 5, 14, 0.05F);
		chestLidLeft.setRotationPoint(7F, 15F, 0F);

		chestBottom = new ModelRenderer(this, 0, 20);
		chestBottom.addBox(-7F, -10F, -7F, 14, 10, 14);
		chestBottom.setRotationPoint(0F, 24F, 0F);

		chestKnob = new ModelRenderer(this, 0, 0);
		chestKnob.addBox(6F, -6F, -2F, 2, 1, 4);
		chestKnob.setRotationPoint(-7F, 15F, 0F);
	}

	public void setRotationAngles(float lidRotation) {
		chestLidLeft.rotateAngleZ = lidRotation * (float) Math.PI / 2.0F;
		chestKnob.rotateAngleZ = chestLidRight.rotateAngleZ = -chestLidLeft.rotateAngleZ;
	}

	public void renderAll() {
		chestLidLeft.render(0.0625F);
		chestLidRight.render(0.0625F);
		chestKnob.render(0.0625F);
		chestBottom.render(0.0625F);
	}
}