package ganymedes01.ganysnether.core.utils.xml;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class OreStack {

	public final String ore;
	public final int size;

	public OreStack(String str, int size) {
		this.ore = str;
		this.size = size;
	}

	public OreStack(String str) {
		this(str, 1);
	}
}