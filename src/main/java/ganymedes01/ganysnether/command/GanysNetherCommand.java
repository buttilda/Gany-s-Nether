package ganymedes01.ganysnether.command;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class GanysNetherCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return Reference.MOD_ID;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/" + Reference.MOD_ID + " <subCommand> [argument]";
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
		if (args.length == 2) {
			String[] array = new String[EntityList.stringToClassMapping.size()];
			int i = 0;
			for (Entry<String, Class> entry : (Set<Entry<String, Class>>) EntityList.stringToClassMapping.entrySet())
				array[i++] = entry.getKey();
			return getListOfStringsMatchingLastWord(args, array);
		}
		return getListOfStringsMatchingLastWord(args, new String[] { "KillAll", "EntityMap" });
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		try {
			String type = args[0];
			if (type.equalsIgnoreCase("KillAll"))
				killAllCommand(sender, args[1]);
			else if (type.equalsIgnoreCase("EntityMap"))
				entityMapCommand(sender);
			else
				sender.addChatMessage(new ChatComponentText(String.format(Utils.getLocString("unknownargumenttry"), type, "KillAll", "EntityMap")));
		} catch (Exception e) {
			throw new WrongUsageException(getCommandUsage(sender), new Object[0]);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void entityMapCommand(ICommandSender sender) throws Exception {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		for (Entity entity : (List<Entity>) sender.getEntityWorld().loadedEntityList) {
			String name = (String) EntityList.classToStringMapping.get(entity.getClass());
			if (name != null) {
				Integer count = map.get(name);
				count = count == null ? 1 : count + 1;
				map.put(name, count);
			}
		}
		List<Entry<String, Integer>> set = new ArrayList(map.entrySet());

		Collections.sort(set, new Comparator() {

			@Override
			public int compare(Object obj1, Object obj2) {
				Entry<String, Integer> entry1 = (Entry<String, Integer>) obj1;
				Entry<String, Integer> entry2 = (Entry<String, Integer>) obj2;
				return entry2.getValue() - entry1.getValue();
			}
		});

		for (Entry<String, Integer> entry : set)
			sender.addChatMessage(new ChatComponentText(entry.getKey() + ": " + entry.getValue()));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void killAllCommand(ICommandSender sender, String arg) throws Exception {
		if (arg.equals("Horse"))
			arg = "EntityHorse";
		Class cls = (Class) EntityList.stringToClassMapping.get(arg);
		if (cls != null) {
			int count = 0;
			for (Entity entity : (List<Entity>) sender.getEntityWorld().loadedEntityList)
				if (entity != null)
					if (cls.isAssignableFrom(entity.getClass())) {
						entity.setDead();
						count++;
					}
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Removed " + count + " entities of type " + arg));
		} else
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Entity class not found: " + arg));
	}
}