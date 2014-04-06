package ganymedes01.ganysnether.client.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

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

	private final ModelRenderer head, mouthTop, mouthBottom, neck, body;
	private final ModelRenderer backLeftLeg, backLeftShin, backRightLeg, backRightShin;
	private final ModelRenderer frontRightLeg, frontLeftShin, frontLeftLeg, frontRightShin;

	public ModelHorseArmourStand() {
		textureWidth = 128;
		textureHeight = 128;
		body = new ModelRenderer(this, 0, 34);
		body.addBox(-5.0F, -8.0F, -19.0F, 10, 10, 24);
		backLeftLeg = new ModelRenderer(this, 78, 29);
		backLeftLeg.addBox(-2.5F, -2.0F, -2.5F, 4, 9, 5);
		backLeftShin = new ModelRenderer(this, 78, 43);
		backLeftShin.addBox(-2.0F, 0.0F, -1.5F, 3, 5, 3);
		backRightLeg = new ModelRenderer(this, 96, 29);
		backRightLeg.addBox(-1.5F, -2.0F, -2.5F, 4, 9, 5);
		backRightShin = new ModelRenderer(this, 96, 43);
		backRightShin.addBox(-1.0F, 0.0F, -1.5F, 3, 5, 3);
		frontRightLeg = new ModelRenderer(this, 44, 29);
		frontRightLeg.addBox(-1.9F, -1.0F, -2.1F, 3, 8, 4);
		frontLeftShin = new ModelRenderer(this, 44, 41);
		frontLeftShin.addBox(-1.9F, 0.0F, -1.6F, 3, 5, 3);
		frontLeftLeg = new ModelRenderer(this, 60, 29);
		frontLeftLeg.addBox(-1.1F, -1.0F, -2.1F, 3, 8, 4);
		frontRightShin = new ModelRenderer(this, 60, 41);
		frontRightShin.addBox(-1.1F, 0.0F, -1.6F, 3, 5, 3);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-2.5F, -10.0F, -1.5F, 5, 5, 7);
		mouthTop = new ModelRenderer(this, 24, 18);
		mouthTop.addBox(-2.0F, -10.0F, -7.0F, 4, 3, 6);
		mouthBottom = new ModelRenderer(this, 24, 27);
		mouthBottom.addBox(-2.0F, -7.0F, -6.5F, 4, 2, 5);
		head.addChild(mouthTop);
		head.addChild(mouthBottom);
		neck = new ModelRenderer(this, 0, 12);
		neck.addBox(-2.05F, -9.8F, -2.0F, 4, 14, 8);

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
		neck.render(0.0625F);
		head.render(0.0625F);
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
		setAngles(0.5235988F, 0.0F, 0.0F, 0.0F, 4.0F, -10.0F, head, neck);
		setAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.02F, 0.02F, mouthTop);
		setAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, mouthBottom);
		setAngles(0.0F, 0.0F, 0.0F, 0.0F, 11.0F, 9.0F, body);
		setAngles(0.0F, 0.0F, 0.0F, 4.0F, 9.0F, 11.0F, backLeftLeg);
		setAngles(0.0F, 0.0F, 0.0F, 4.0F, 16.0F, 11.0F, backLeftShin);
		setAngles(0.0F, 0.0F, 0.0F, -4.0F, 9.0F, 11.0F, backRightLeg);
		setAngles(0.0F, 0.0F, 0.0F, -4.0F, 16.0F, 11.0F, backRightShin);
		setAngles(0.0F, 0.0F, 0.0F, 4.0F, 9.0F, -8.0F, frontRightLeg);
		setAngles(0.0F, 0.0F, 0.0F, 4.0F, 16.0F, -8.0F, frontLeftShin);
		setAngles(0.0F, 0.0F, 0.0F, -4.0F, 9.0F, -8.0F, frontLeftLeg);
		setAngles(0.0F, 0.0F, 0.0F, -4.0F, 16.0F, -8.0F, frontRightShin);
	}

	private void createFile() {
		try {
			for (Field field : this.getClass().getDeclaredFields())
				if (field.get(this) instanceof ModelRenderer)
					writeFile((ModelRenderer) field.get(this), field.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeFile(ModelRenderer model, String name) {
		try {
			File f = new File("/Users/Andre/Desktop/HorseAngles.txt");
			if (!f.exists())
				f.createNewFile();

			FileWriter fw = new FileWriter(f.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("setAngles(" + name + ", " + model.rotateAngleX + "F, " + model.rotateAngleY + "F, " + model.rotateAngleZ + "F, " + model.rotationPointX + "F, " + model.rotationPointY + "F, " + model.rotationPointZ + "F);\n");

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}