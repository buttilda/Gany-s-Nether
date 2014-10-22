package ganymedes01.ganysnether.core.utils.xml;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class XMLBuilder {

	private final XMLNode node;

	public XMLBuilder(String name) {
		node = new XMLNode(name);
	}

	public void makeEntries(String key, Object[] objs) {
		for (int i = 0; i < objs.length; i++)
			makeEntry(key + (i + 1), objs[i]);
	}

	public void makeEntry(String key, Object obj) {
		node.addNode(new XMLNode(key).setValue(XMLHelper.toNodeValue(obj)));
	}

	@Override
	public String toString() {
		return node.toString();
	}

	public XMLNode toNode() {
		return node;
	}
}