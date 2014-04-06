package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.WeightedRandomMinecart;
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

	public boolean redstoneUpgrade;
	public boolean noPlayerUpgrade;
	public boolean ignoreLightUpgrade;
	public boolean silkyUpgrade;

	private final TileEntity tile;
	private final short maxNearbyEntities = 6;
	private final short minSpawnDelay = 200;
	private final short maxSpawnDelay = 800;
	private short spawnCount = 4;
	private short spawnRange = 4;

	public ExtendedSpawnerLogic(TileEntity tile) {
		this.tile = tile;
	}

	@Override
	public boolean canRun() {
		boolean redstone = redstoneUpgrade ? !isBlockPowered : true;
		boolean player = noPlayerUpgrade || super.canRun();

		return redstone && player;
	}

	public int getSpawnCountUpgradeCount() {
		return spawnCount - 4;
	}

	public int getSpawnRangeUpgradeCount() {
		return spawnRange - 4;
	}

	@Override
	public void updateSpawner() {
		if (!canRun())
			return;

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
			Entity entity = EntityList.createEntityByName(getEntityNameToSpawn(), world);

			if (entity == null)
				return;

			int entityCount = world.getEntitiesWithinAABB(entity.getClass(), AxisAlignedBB.getAABBPool().getAABB(getSpawnerX(), getSpawnerY(), getSpawnerZ(), getSpawnerX() + 1, getSpawnerY() + 1, getSpawnerZ() + 1).expand(spawnRange * 2, 4.0D, spawnRange * 2)).size();
			if (entityCount >= maxNearbyEntities) {
				resetDelay();
				return;
			}

			double x = getSpawnerX() + (world.rand.nextDouble() - world.rand.nextDouble()) * spawnRange;
			double y = getSpawnerY() + world.rand.nextInt(3) - 1;
			double z = getSpawnerZ() + (world.rand.nextDouble() - world.rand.nextDouble()) * spawnRange;
			EntityLiving entityliving = entity instanceof EntityLiving ? (EntityLiving) entity : null;
			entity.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);

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

	private void resetDelay() {
		spawnDelay = minSpawnDelay + getSpawnerWorld().rand.nextInt(maxSpawnDelay - minSpawnDelay);
		func_98267_a(1);
	}

	private boolean canSpawn(EntityLiving entity) {
		boolean isNull = entity == null;
		if (ignoreLightUpgrade)
			return isNull || entity.worldObj.checkNoEntityCollision(entity.boundingBox) && entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty() && !entity.worldObj.isAnyLiquid(entity.boundingBox);
		return isNull || entity.getCanSpawnHere();
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
	public void func_98267_a(int eventID) {
		getSpawnerWorld().addBlockEvent(getSpawnerX(), getSpawnerY(), getSpawnerZ(), ModBlocks.extendedSpawner.blockID, eventID, 0);
	}

	@Override
	public World getSpawnerWorld() {
		return tile.worldObj;
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
	public void setRandomMinecart(WeightedRandomMinecart minecart) {
	}

	@Override
	public WeightedRandomMinecart getRandomMinecart() {
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		spawnCount = data.getShort("SpawnCount");
		spawnRange = data.getShort("SpawnRange");

		isBlockPowered = data.getBoolean("isBlockPowered");
		redstoneUpgrade = data.getBoolean("redstoneUpgrade");
		noPlayerUpgrade = data.getBoolean("noPlayerUpgrade");
		ignoreLightUpgrade = data.getBoolean("ignoreLightUpgrade");
		silkyUpgrade = data.getBoolean("silkyUpgrade");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setShort("SpawnCount", spawnCount);
		data.setShort("SpawnRange", spawnRange);

		data.setBoolean("isBlockPowered", isBlockPowered);
		data.setBoolean("redstoneUpgrade", redstoneUpgrade);
		data.setBoolean("noPlayerUpgrade", noPlayerUpgrade);
		data.setBoolean("ignoreLightUpgrade", ignoreLightUpgrade);
		data.setBoolean("silkyUpgrade", silkyUpgrade);
	}
}