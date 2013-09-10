package ganymedes01.ganysnether.lib;

import ganymedes01.ganysnether.blocks.ModBlocks;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class BlocksID {

	/* Default */
	// Blocks
	public static int TILLED_NETHERRACK_ID_DEFAULT = 1600;
	public static int QUARZ_BERRY_BUSH_ID_DEFAULT = 1601;
	public static int SPECTRE_WHEAT_ID_DEFAULT = 1602;
	public static int GLOWING_REED_ID_DEFAULT = 1603;
	public static int SOUL_GLASS_ID_DEFAULT = 1604;
	public static int SOUL_CHEST_ID_DEFAULT = 1605;
	public static int VOLCANIC_FURNACE_IDLE_ID_DEFAULT = 1606;
	public static int VOLCANIC_FURNACE_ACTIVE_ID_DEFAULT = 1607;
	public static int DENSE_LAVA_CELL_ID_DEFAULT = 1608;
	public static int GLOW_BOX_ID_DEFAULT = 1609;
	public static int COLOURED_QUARTZ_BLOCK_ID_DEFAULT = 1610;
	public static int COLOURED_CHISELLED_QUARTZ_BLOCK_ID_DEFAULT = 1610;
	public static int[] COLOURED_QUARTZ_STAIRS_IDS_DEFAULT = registerStairIDS(1611, ModBlocks.colouredQuartzBlockStairs.length);
	public static int SOUL_GLASS_STAIRS_ID_DEFAULT = 1627;
	public static int[] COLOURED_QUARTZ_PILLARS_IDS_DEFAULT = registerStairIDS(1628, ModBlocks.colouredQuartzPillar.length);
	public static int REPRODUCER_ID_DEFAULT = 1632;

	/* Current */
	// Blocks
	public static int TILLED_NETHERRACK_ID;
	public static int QUARZ_BERRY_BUSH_ID;
	public static int SPECTRE_WHEAT_ID;
	public static int GLOWING_REED_ID;
	public static int SOUL_GLASS_ID;
	public static int SOUL_CHEST_ID;
	public static int VOLCANIC_FURNACE_IDLE_ID;
	public static int VOLCANIC_FURNACE_ACTIVE_ID;
	public static int DENSE_LAVA_CELL_ID;
	public static int GLOW_BOX_ID;
	public static int COLOURED_QUARTZ_BLOCK_ID;
	public static int COLOURED_CHISELLED_QUARTZ_BLOCK_ID;
	public static int[] COLOURED_QUARTZ_STAIRS_IDS = new int[ModBlocks.colouredQuartzBlockStairs.length];
	public static int SOUL_GLASS_STAIRS_ID;
	public static int[] COLOURED_QUARTZ_PILLARS_IDS = new int[ModBlocks.colouredQuartzPillar.length];
	public static int REPRODUCER_ID;

	private static int[] registerStairIDS(int base, int num) {
		int[] ids = new int[num];
		for (int i = 0; i < num; i++)
			ids[i] = base + i;
		return ids;
	}
}
