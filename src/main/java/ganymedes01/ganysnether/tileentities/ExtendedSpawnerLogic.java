package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.InventoryUtils;
import ganymedes01.ganysnether.core.utils.SpawnEggHelper;
import ganymedes01.ganysnether.items.SkeletonSpawner;
import ganymedes01.ganysnether.items.SpawnerUpgrade.UpgradeType;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ExtendedSpawnerLogic extends MobSpawnerBaseLogic {

	public boolean isBlockPowered;
	public boolean isWitherSkeleton;

	public boolean redstoneUpgrade;
	public boolean noPlayerUpgrade;
	public boolean ignoreConditionsUpgrade;
	public boolean silkyUpgrade;
	public byte tier;

	private final TileEntity tile;
	private final short minSpawnDelay = 200;
	private final short maxSpawnDelay = 800;
	private final ItemStack[] fifo = new ItemStack[3];
	public short maxNearbyEntities = 6;
	public short spawnCount = 4;
	public short spawnRange = 4;

	public ExtendedSpawnerLogic(TileEntity tile) {
		this.tile = tile;
	}

	@Override
	public boolean isActivated() {
		boolean redstone = redstoneUpgrade ? !isBlockPowered : true;
		boolean player = noPlayerUpgrade || super.isActivated();

		return redstone && player;
	}

	public int getSpawnCountUpgradeCount() {
		return spawnCount - 4;
	}

	public int getSpawnRangeUpgradeCount() {
		return spawnRange - 4;
	}

	public ItemStack[] getFifo() {
		return fifo;
	}

	public boolean addEgg(ItemStack egg) {
		if (tier != UpgradeType.tierDragonEgg.ordinal())
			return false;
		ItemStack stack = egg == null ? null : egg.copy().splitStack(1);

		if (egg != null && egg.getItem() instanceof SkeletonSpawner)
			isWitherSkeleton = egg.getItemDamage() == 1;

		for (int i = 0; i < fifo.length; i++)
			if (fifo[i] == null) {
				fifo[i] = stack;
				return true;
			}
		InventoryUtils.dropStack(getSpawnerWorld(), getSpawnerX(), getSpawnerY() + 1, getSpawnerZ(), fifo[0]);
		for (int i = 0; i < fifo.length - 1; i++)
			fifo[i] = fifo[i + 1];
		fifo[fifo.length - 1] = stack;

		for (int i = 0; i < fifo.length; i++) {
			if (fifo[i] == null || !(fifo[i].getItem() instanceof SkeletonSpawner))
				continue;
			isWitherSkeleton = fifo[i].getItemDamage() == 1;
		}
		return true;
	}

	@Override
	public void updateSpawner() {
		if (!isActivated()) {
			field_98284_d = 0;
			field_98287_c = 0;
			return;
		}

		World world = getSpawnerWorld();
		if (world.isRemote) {
			spawnParticles();
			return;
		}

		if (spawnDelay < 0)
			resetDelay();
		if (spawnDelay > 0) {
			spawnDelay--;
			return;
		}

		boolean reset = false;
		for (int i = 0; i < spawnCount; i++) {
			Entity entity = getEntityToSpawn(world);

			if (entity == null)
				return;

			int entityCount = world.getEntitiesWithinAABB(entity.getClass(), AxisAlignedBB.getBoundingBox(getSpawnerX(), getSpawnerY(), getSpawnerZ(), getSpawnerX() + 1, getSpawnerY() + 1, getSpawnerZ() + 1).expand(spawnRange * 2, 4.0D, spawnRange * 2)).size();
			if (entityCount >= maxNearbyEntities) {
				resetDelay();
				return;
			}

			EntityLiving entityliving = entity instanceof EntityLiving ? (EntityLiving) entity : null;
			if (canSpawn(entityliving)) {
				func_98265_a(entity);
				world.playAuxSFX(2004, getSpawnerX(), getSpawnerY(), getSpawnerZ(), 0);

				if (entityliving != null)
					entityliving.spawnExplosionParticle();

				reset = true;
			}
		}

		if (reset)
			resetDelay();
	}

	private Entity getEntityToSpawn(World world) {
		double x = getSpawnerX() + (world.rand.nextDouble() - world.rand.nextDouble()) * spawnRange;
		double y = getSpawnerY() + world.rand.nextInt(3) - 1;
		double z = getSpawnerZ() + (world.rand.nextDouble() - world.rand.nextDouble()) * spawnRange;

		if (tier == UpgradeType.tierDragonEgg.ordinal()) {
			List<Integer> notNull = new ArrayList<Integer>();
			for (int i = 0; i < fifo.length; i++)
				if (fifo[i] != null)
					notNull.add(i);

			if (!notNull.isEmpty()) {
				ItemStack egg = fifo[notNull.get(world.rand.nextInt(notNull.size()))];
				return SpawnEggHelper.getEntity(world, x, y, z, egg);
			}
			return null;
		}
		Entity entity = EntityList.createEntityByName(getEntityNameToSpawn(), world);
		if (entity != null)
			entity.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
		return entity;
	}

	private void resetDelay() {
		int min = (int) (minSpawnDelay - Math.pow(2, tier));
		int max = (int) (maxSpawnDelay - 4 * Math.pow(2, tier));

		spawnDelay = min + getSpawnerWorld().rand.nextInt(max - min);
		func_98267_a(1);
	}

	private boolean canSpawn(EntityLiving entity) {
		if (entity == null)
			return true;

		if (ignoreConditionsUpgrade)
			return entity.worldObj.checkNoEntityCollision(entity.boundingBox) && entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty() && !entity.worldObj.isAnyLiquid(entity.boundingBox);
		if (entity instanceof EntitySkeleton)
			if (((EntitySkeleton) entity).getSkeletonType() == 1 && !entity.worldObj.provider.isHellWorld)
				return false;
		return entity.getCanSpawnHere();
	}

	@Override
	public Entity func_98265_a(Entity entity) {
		if (entity instanceof EntityLivingBase && entity.worldObj != null) {
			((EntityLiving) entity).onSpawnWithEgg((IEntityLivingData) null);

			if (entity instanceof EntitySkeleton) {
				EntitySkeleton skeleton = (EntitySkeleton) entity;
				skeleton.setSkeletonType(isWitherSkeleton ? 1 : 0);
				if (isWitherSkeleton) {
					skeleton.tasks.addTask(4, new EntityAIAttackOnCollide(skeleton, EntityPlayer.class, 1.2D, false));
					skeleton.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
					skeleton.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
				} else {
					skeleton.tasks.addTask(4, new EntityAIArrowAttack(skeleton, 1.0D, 20, 60, 15.0F));
					SkeletonSpawner.addRandomArmor(skeleton);
					SkeletonSpawner.enchantEquipment(skeleton);
				}
				skeleton.setSkeletonType(isWitherSkeleton ? 1 : 0);
			}
			getSpawnerWorld().spawnEntityInWorld(entity);
		}

		return entity;
	}

	@SideOnly(Side.CLIENT)
	private void spawnParticles() {
		double x = getSpawnerX() + getSpawnerWorld().rand.nextFloat();
		double y = getSpawnerY() + getSpawnerWorld().rand.nextFloat();
		double z = getSpawnerZ() + getSpawnerWorld().rand.nextFloat();
		getSpawnerWorld().spawnParticle("smoke", x, y, z, 0.0D, 0.0D, 0.0D);
		getSpawnerWorld().spawnParticle("flame", x, y, z, 0.0D, 0.0D, 0.0D);

		if (spawnDelay > 0)
			spawnDelay--;

		field_98284_d = field_98287_c;
		field_98287_c = (field_98287_c + 1000.0F / (spawnDelay + 200.0F)) % 360.0D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Entity func_98281_h() {
		return tier == UpgradeType.tierDragonEgg.ordinal() ? new EntityItem(getSpawnerWorld(), getSpawnerX(), getSpawnerY(), getSpawnerZ(), new ItemStack(Blocks.dragon_egg)) : super.func_98281_h();
	}

	@Override
	public void func_98267_a(int eventID) {
		getSpawnerWorld().addBlockEvent(getSpawnerX(), getSpawnerY(), getSpawnerZ(), ModBlocks.extendedSpawner, eventID, 0);
	}

	@Override
	public World getSpawnerWorld() {
		return tile.getWorldObj();
	}

	@Override
	public int getSpawnerX() {
		return tile.xCoord;
	}

	@Override
	public int getSpawnerY() {
		return tile.yCoord;
	}

	@Override
	public int getSpawnerZ() {
		return tile.zCoord;
	}

	@Override
	public void setRandomEntity(WeightedRandomMinecart minecart) {
	}

	@Override
	public WeightedRandomMinecart getRandomEntity() {
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		spawnCount = data.getShort("SpawnCount");
		spawnRange = data.getShort("SpawnRange");
		maxNearbyEntities = data.getShort("MaxNearbyEntities");

		isBlockPowered = data.getBoolean("isBlockPowered");
		redstoneUpgrade = data.getBoolean("redstoneUpgrade");
		noPlayerUpgrade = data.getBoolean("noPlayerUpgrade");
		ignoreConditionsUpgrade = data.getBoolean("ignoreLightUpgrade");
		silkyUpgrade = data.getBoolean("silkyUpgrade");
		tier = data.getByte("tier");
		isWitherSkeleton = data.getBoolean("isWitherSkeleton");

		NBTTagList tagList = data.getTagList("Items", 10);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < fifo.length)
				fifo[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setShort("SpawnCount", spawnCount);
		data.setShort("SpawnRange", spawnRange);
		data.setShort("MaxNearbyEntities", maxNearbyEntities);

		data.setBoolean("isBlockPowered", isBlockPowered);
		data.setBoolean("redstoneUpgrade", redstoneUpgrade);
		data.setBoolean("noPlayerUpgrade", noPlayerUpgrade);
		data.setBoolean("ignoreLightUpgrade", ignoreConditionsUpgrade);
		data.setBoolean("silkyUpgrade", silkyUpgrade);
		data.setByte("tier", tier);
		data.setBoolean("isWitherSkeleton", isWitherSkeleton);

		NBTTagList tagList = new NBTTagList();
		for (int i = 0; i < fifo.length; i++)
			if (fifo[i] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) i);
				fifo[i].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		data.setTag("Items", tagList);
	}
}