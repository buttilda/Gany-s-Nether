package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SkeletonSpawner extends Item {

	@SideOnly(Side.CLIENT)
	private Icon overlay;

	public SkeletonSpawner() {
		super(ModIDs.SKELETON_SPAWNER_ID);
		setMaxDamage(0);
		setHasSubtypes(true);
		setTextureName("spawn_egg");
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SKELETON_SPAWNER_NAME));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Utils.getUnlocalizedName(Strings.SKELETON_SPAWNER_NAME) + stack.getItemDamage();
	}

	public static Entity spawnSkeleton(World world, double x, double y, double z, int type) {
		if (!EntityList.entityEggs.containsKey(Integer.valueOf(51)))
			return null;
		else {
			EntitySkeleton skeleton = null;
			Entity entity = EntityList.createEntityByID(51, world);
			if (entity instanceof EntitySkeleton) {
				skeleton = (EntitySkeleton) entity;
				if (skeleton != null) {
					skeleton.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
					skeleton.rotationYawHead = skeleton.rotationYaw;
					skeleton.renderYawOffset = skeleton.rotationYaw;
					skeleton.onSpawnWithEgg((EntityLivingData) null);
					skeleton.setSkeletonType(type);
					if (type == 1) {
						skeleton.tasks.addTask(4, new EntityAIAttackOnCollide(skeleton, EntityPlayer.class, 1.2D, false));
						skeleton.setCurrentItemOrArmor(0, new ItemStack(Item.swordStone));
						skeleton.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
					} else if (type == 0) {
						skeleton.tasks.addTask(4, new EntityAIArrowAttack(skeleton, 1.0D, 20, 60, 15.0F));
						addRandomArmor(skeleton);
						enchantEquipment(skeleton);
					}
					skeleton.setSkeletonType(type);
					world.spawnEntityInWorld(skeleton);
					skeleton.playLivingSound();
				}
			}
			return skeleton;
		}
	}

	public static void enchantEquipment(EntitySkeleton skeleton) {
		Random rand = new Random();
		float f = skeleton.worldObj.getLocationTensionFactor(skeleton.posX, skeleton.posY, skeleton.posZ);
		if (skeleton.getHeldItem() != null && rand.nextFloat() < 0.25F * f)
			EnchantmentHelper.addRandomEnchantment(rand, skeleton.getHeldItem(), (int) (5.0F + f * rand.nextInt(18)));
		for (int i = 0; i < 4; ++i) {
			ItemStack itemstack = skeleton.func_130225_q(i);
			if (itemstack != null && rand.nextFloat() < 0.5F * f)
				EnchantmentHelper.addRandomEnchantment(rand, itemstack, (int) (5.0F + f * rand.nextInt(18)));
		}
	}

	public static void addRandomArmor(EntitySkeleton skeleton) {
		Random rand = new Random();
		if (rand.nextFloat() < 0.15F * skeleton.worldObj.getLocationTensionFactor(skeleton.posX, skeleton.posY, skeleton.posZ)) {
			int i = rand.nextInt(2);
			float f = skeleton.worldObj.difficultySetting == 3 ? 0.1F : 0.25F;

			if (rand.nextFloat() < 0.095F)
				++i;
			if (rand.nextFloat() < 0.095F)
				++i;
			if (rand.nextFloat() < 0.095F)
				++i;
			for (int j = 3; j >= 0; --j) {
				ItemStack itemstack = skeleton.func_130225_q(j);

				if (j < 3 && rand.nextFloat() < f)
					break;
				if (itemstack == null) {
					Item item = EntityLiving.getArmorItemForSlot(j + 1, i);
					if (item != null)
						skeleton.setCurrentItemOrArmor(j + 1, new ItemStack(item));
				}
			}
		}
		skeleton.setCurrentItemOrArmor(0, new ItemStack(Item.bow));
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		else {
			int id = world.getBlockId(x, y, z);
			x += Facing.offsetsXForSide[side];
			y += Facing.offsetsYForSide[side];
			z += Facing.offsetsZForSide[side];
			double yOffSet = 0.0D;

			if (side == 1 && Block.blocksList[id] != null && Block.blocksList[id].getRenderType() == 11)
				yOffSet = 0.5D;

			Entity entity = spawnSkeleton(world, x + 0.5D, y + yOffSet, z + 0.5D, stack.getItemDamage());

			if (entity != null) {
				if (entity instanceof EntityLivingBase && stack.hasDisplayName())
					((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());

				if (!player.capabilities.isCreativeMode)
					stack.stackSize--;
			}

			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		super.registerIcons(reg);
		overlay = reg.registerIcon("spawn_egg_overlay");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamageForRenderPass(int meta, int pass) {
		return pass > 0 ? overlay : super.getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tab, List list) {
		list.add(new ItemStack(itemID, 1, 0));
		list.add(new ItemStack(itemID, 1, 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		if (stack.getItemDamage() == 0)
			return pass == 0 ? 12698049 : 4802889;
		else
			return pass == 0 ? 4802889 : 12698049;
	}
}