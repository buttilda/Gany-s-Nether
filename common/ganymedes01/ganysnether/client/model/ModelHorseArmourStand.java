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
public class ModelHorseArmourStand extends ModelBase {

	private final ModelRenderer head, mouthTop, mouthBottom, leftEar, rightEar, neck, mane;
	private final ModelRenderer body, tailBase;
	private final ModelRenderer backLeftLeg, backLeftShin;
	private final ModelRenderer backRightLeg, backRightShin;
	private final ModelRenderer frontRightLeg, frontLeftShin;
	private final ModelRenderer frontLeftLeg, frontRightShin;
	private final ModelRenderer base;

	public ModelHorseArmourStand() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this, 0, 34);
		body.addBox(-5, -8, -19, 10, 10, 24);
		tailBase = new ModelRenderer(this, 44, 0);
		tailBase.addBox(-1, -1, 0, 2, 2, 3);
		backLeftLeg = new ModelRenderer(this, 78, 29);
		backLeftLeg.addBox(-2.5F, -2, -2.5F, 4, 9, 5);
		backLeftShin = new ModelRenderer(this, 78, 43);
		backLeftShin.addBox(-2, 0, -1.5F, 3, 5, 3);
		backRightLeg = new ModelRenderer(this, 96, 29);
		backRightLeg.addBox(-1.5F, -2, -2.5F, 4, 9, 5);
		backRightShin = new ModelRenderer(this, 96, 43);
		backRightShin.addBox(-1, 0, -1.5F, 3, 5, 3);
		frontRightLeg = new ModelRenderer(this, 44, 29);
		frontRightLeg.addBox(-1.9F, -1, -2.1F, 3, 8, 4);
		frontLeftShin = new ModelRenderer(this, 44, 41);
		frontLeftShin.addBox(-1.9F, 0, -1.6F, 3, 5, 3);
		frontLeftLeg = new ModelRenderer(this, 60, 29);
		frontLeftLeg.addBox(-1.1F, -1, -2.1F, 3, 8, 4);
		frontRightShin = new ModelRenderer(this, 60, 41);
		frontRightShin.addBox(-1.1F, 0, -1.6F, 3, 5, 3);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-2.5F, -10, -1.5F, 5, 5, 7);
		mouthTop = new ModelRenderer(this, 24, 18);
		mouthTop.addBox(-2, -10, -7, 4, 3, 6);
		mouthBottom = new ModelRenderer(this, 24, 27);
		mouthBottom.addBox(-2, -7, -6.5F, 4, 2, 5);
		head.addChild(mouthTop);
		head.addChild(mouthBottom);
		leftEar = new ModelRenderer(this, 0, 0);
		leftEar.addBox(0.45F, -12, 4, 2, 3, 1);
		rightEar = new ModelRenderer(this, 0, 0);
		rightEar.addBox(-2.45F, -12, 4, 2, 3, 1);
		neck = new ModelRenderer(this, 0, 12);
		neck.addBox(-2.05F, -9.8F, -2, 4, 14, 8);
		mane = new ModelRenderer(this, 58, 0);
		mane.addBox(-1, -11.5F, 5, 2, 16, 4);
		base = new ModelRenderer(this, 0, 68);
		base.addBox(-8, 21, -16, 16, 3, 32);

		setAllAngles();
	}

	public void renderAll() {
		backLeftLeg.render(0.0625F);
		backLeftShin.render(0.0625F);
		backRightLeg.render(0.0625F);
		backRightShin.render(0.0625F);
		frontRightLeg.render(0.0625F);
		frontLeftShin.render(0.0625F);
		frontLeftLeg.render(0.0625F);
		frontRightShin.render(0.0625F);
		body.render(0.0625F);
		tailBase.render(0.0625F);
		neck.render(0.0625F);
		mane.render(0.0625F);
		leftEar.render(0.0625F);
		rightEar.render(0.0625F);
		head.render(0.0625F);
		base.render(0.0625F);
	}

	private void setAngles(float x, float y, float z, float px, float py, float pz, ModelRenderer... models) {
		for (ModelRenderer model : models) {
			model.rotateAngleX = x;
			model.rotateAngleY = y;
			model.rotateAngleZ = z;

			model.setRotationPoint(px, py, pz);
		}
	}

	private void setAllAngles() {
		setAngles(0.5235988F, 0, 0, 0, 4, -10, head, leftEar, rightEar, neck, mane);
		setAngles(0, 0, 0, 0, 0.02F, 0.02F, mouthTop);
		setAngles(0, 0, 0, 0, 0, 0, mouthBottom, base);
		setAngles(0, 0, 0, 0, 11, 9, body);
		setAngles(-1.3089F, 0, 0, 0, 3, 14, tailBase);
		setAngles(0, 0, 0, 4, 9, 11, backLeftLeg);
		setAngles(0, 0, 0, 4, 16, 11, backLeftShin);
		setAngles(0, 0, 0, -4, 9, 11, backRightLeg);
		setAngles(0, 0, 0, -4, 16, 11, backRightShin);
		setAngles(0, 0, 0, 4, 9, -8, frontRightLeg);
		setAngles(0, 0, 0, 4, 16, -8, frontLeftShin);
		setAngles(0, 0, 0, -4, 9, -8, frontLeftLeg);
		setAngles(0, 0, 0, -4, 16, -8, frontRightShin);
	}
}