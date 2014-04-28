package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.lib.Strings;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TileEntitySoulChest extends GanysInventory {

	public float lidAngle;
	public float prevLidAngle;
	public int numUsingPlayers;

	public TileEntitySoulChest() {
		this(27, Strings.Blocks.SOUL_CHEST_NAME);
	}

	public TileEntitySoulChest(int size, String name) {
		super(size, name);
	}

	@Override
	public void updateEntity() {
		prevLidAngle = lidAngle;

		if (numUsingPlayers > 0 && lidAngle == 0.0F)
			worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);

		if (numUsingPlayers <= 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F) {
			float initialLidAngle = lidAngle;

			if (numUsingPlayers > 0)
				lidAngle += 0.1F;
			else
				lidAngle -= 0.1F;

			if (lidAngle > 1.0F)
				lidAngle = 1.0F;
			else if (lidAngle < 0.0F)
				lidAngle = 0.0F;

			if (lidAngle < 0.5F && initialLidAngle >= 0.5F)
				worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}
	}

	@Override
	public boolean receiveClientEvent(int eventType, int arg) {
		if (eventType == 1) {
			numUsingPlayers = arg;
			return true;
		}
		return false;
	}

	@Override
	public void openInventory() {
		if (numUsingPlayers < 0)
			numUsingPlayers = 0;

		numUsingPlayers++;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 1, numUsingPlayers);
	}

	@Override
	public void closeInventory() {
		numUsingPlayers--;
		if (numUsingPlayers < 0)
			numUsingPlayers = 0;

		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 1, numUsingPlayers);
	}
}