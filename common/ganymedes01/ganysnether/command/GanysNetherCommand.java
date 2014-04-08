package ganymedes01.ganysnether.command;

import ganymedes01.ganysnether.lib.Reference;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IntHashMap;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.relauncher.ReflectionHelper;

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
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
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
			else {
				EnumChatFormatting red = EnumChatFormatting.RED;
				EnumChatFormatting gold = EnumChatFormatting.GOLD;
				sender.sendChatToPlayer(ChatMessageComponent.createFromText(red + "Unknown argument: " + type + ". Try " + gold + "KillAll" + red + " or " + gold + "EntityMap" + red + "!"));
			}
		} catch (Exception e) {
			throw new WrongUsageException(getCommandUsage(sender), new Object[0]);
		}
	}

	private void entityMapCommand(ICommandSender sender) throws Exception {
		WorldServer world = (WorldServer) sender.getEntityWorld();
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		for (Entity entity : getAllEntities(world)) {
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
			sender.sendChatToPlayer(ChatMessageComponent.createFromText(entry.getKey() + ": " + entry.getValue()));
	}

	private void killAllCommand(ICommandSender sender, String arg) throws Exception {
		WorldServer world = (WorldServer) sender.getEntityWorld();
		if (arg.equals("Horse"))
			arg = "EntityHorse";
		Class cls = (Class) EntityList.stringToClassMapping.get(arg);
		if (cls != null) {
			int count = 0;
			for (Entity entity : getAllEntities(world))
				if (entity != null)
					if (cls.isAssignableFrom(entity.getClass())) {
						entity.setDead();
						count++;
					}
			sender.sendChatToPlayer(ChatMessageComponent.createFromText(EnumChatFormatting.GOLD + "Removed " + count + " entities of type " + arg));
		} else
			sender.sendChatToPlayer(ChatMessageComponent.createFromText(EnumChatFormatting.RED + "Entity class not found: " + arg));
	}

	private Method findMethod(Class cls, String... names) {
		for (String name : names)
			try {
				Method method = cls.getMethod(name);
				method.setAccessible(true);
				return method;
			} catch (Exception e) {
				continue;
			}
		return null;
	}

	private Entity[] getAllEntities(WorldServer world) throws Exception {
		ArrayList<Entity> list = new ArrayList<Entity>();
		Field entityIdMap = ReflectionHelper.findField(WorldServer.class, "entityIdMap", "field_73066_T");
		entityIdMap.setAccessible(true);
		Field slots = ReflectionHelper.findField(IntHashMap.class, "slots", "field_76055_a");
		slots.setAccessible(true);

		int count = 0;
		for (Object obj : (Object[]) slots.get(entityIdMap.get(world)))
			if (obj != null) {
				Method m = findMethod(obj.getClass(), "func_76030_b", "getValue");
				list.add((Entity) m.invoke(obj));
			}
		return list.toArray(new Entity[0]);
	}
}