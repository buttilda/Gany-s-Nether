package ganymedes01.ganysnether.lib;

import net.minecraft.block.StepSound;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ModSounds {

	public static final StepSound soundBlaze = new CustomSound("mob.blaze.hit");

	private static final class CustomSound extends StepSound {

		private final boolean useDefaults;

		public CustomSound(String name, float volume, float pitch, boolean useDefaults) {
			super(name, volume, pitch);
			this.useDefaults = useDefaults;
		}

		public CustomSound(String name) {
			this(name, 1.0F, 1.0F, false);
		}

		@Override
		public String getBreakSound() {
			return useDefaults ? super.getBreakSound() : stepSoundName;
		}

		@Override
		public String getStepSound() {
			return useDefaults ? super.getStepSound() : stepSoundName;
		}
	}
}