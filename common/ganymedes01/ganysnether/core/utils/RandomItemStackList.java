package ganymedes01.ganysnether.core.utils;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.items.SceptreOfLightning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class RandomItemStackList {

	private ArrayList<ItemStack> randomItemsList;

	public RandomItemStackList() {
		Random rand = new Random();
		randomItemsList = new ArrayList<ItemStack>();
		randomItemsList.add(new ItemStack(ModItems.witherShrubSeeds));
		randomItemsList.add(new ItemStack(ModItems.skeletonSpawner, 1, 1));
		randomItemsList.add(new ItemStack(ModItems.dimensionalBread, 1, 1 + rand.nextInt(4)));
		randomItemsList.add(new ItemStack(ModItems.quarzBerrySeeds, 1, 1 + rand.nextInt(8)));
		randomItemsList.add(new ItemStack(ModItems.ghostSeeds, 1, 1 + rand.nextInt(8)));
		randomItemsList.add(new ItemStack(ModItems.sceptreOfLightning, 1, 1 - GanysNether.sceptreOfLightningDurability > 0 ? GanysNether.sceptreOfLightningDurability : SceptreOfLightning.DEFAULT_DUR));
		randomItemsList.add(new ItemStack(Block.torchWood));
		randomItemsList.add(new ItemStack(Item.coal, 1 + rand.nextInt(8)));
		randomItemsList.add(new ItemStack(Item.stick, 1 + rand.nextInt(16)));
		randomItemsList.add(new ItemStack(Item.beefCooked, 1 + rand.nextInt(8)));
		randomItemsList.add(new ItemStack(Item.bakedPotato, 1 + rand.nextInt(16)));
		randomItemsList.add(new ItemStack(Item.goldNugget, 1 + rand.nextInt(16)));
		randomItemsList.add(new ItemStack(Item.rottenFlesh));
		randomItemsList.add(new ItemStack(Item.rottenFlesh));
		randomItemsList.add(new ItemStack(Item.rottenFlesh));
		randomItemsList.add(new ItemStack(Item.rottenFlesh));
		randomItemsList.add(new ItemStack(Block.netherBrick));
		randomItemsList.add(new ItemStack(Item.netherStalkSeeds, 1 + rand.nextInt(4)));
		randomItemsList.add(new ItemStack(Block.whiteStone));
	}

	public void shuffle() {
		Collections.shuffle(randomItemsList);
	}

	public ItemStack getListItem(Random rand) {
		ItemStack stack = randomItemsList.get(rand.nextInt(randomItemsList.size()));
		if (stack.stackSize == 1)
			stack.stackSize = rand.nextInt(stack.getMaxStackSize() == 64 ? 16 : stack.getMaxStackSize());
		if (stack.getItem() == ModItems.witherShrubSeeds)
			return rand.nextInt(50) == 25 ? stack : new ItemStack(Item.ghastTear, rand.nextInt(2));
		else
			return stack;
	}
}
