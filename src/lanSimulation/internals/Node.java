/*   This file is part of lanSimulation.
 *
 *   lanSimulation is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   lanSimulation is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with lanSimulation; if not, write to the Free Software
 *   Foundation, Inc. 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *   Copyright original Java version: 2004 Bart Du Bois, Serge Demeyer
 *   Copyright C++ version: 2006 Matthias Rieger, Bart Van Rompaey
 */
package lanSimulation.internals;

import java.io.IOException;
import java.io.Writer;

import lanSimulation.Network;

/**
 * A <em>Node</em> represents a single Node in a Local Area Network (LAN).
 * Several types of Nodes exist.
 */
public class Node {

	/**
	 * Holds the name of the Node.
	 */
	public String name_;
	/**
	 * Holds the next Node in the token ring architecture.
	 * 
	 * @see lanSimulation.internals.Node
	 */
	public Node nextNode_;

	/**
	 * Construct a <em>Node</em> with given #type and #name.
	 * <p>
	 * <strong>Precondition:</strong> (type >= NODE) & (type <= PRINTER);
	 * </p>
	 */
	protected Node(byte type, String name) {
		name_ = name;
		nextNode_ = null;
	}

	/**
	 * Construct a <em>Node</em> with given #name.
	 * <p>
	 * <strong>Precondition:</strong> (type >= NODE) & (type <= PRINTER);
	 * </p>
	 */
	public Node(String name) {
		name_ = name;
		nextNode_ = null;
	}

	/**
	 * Construct a <em>Node</em> with given #name, and which is linked
	 * to #nextNode.
	 * <p>
	 * </p>
	 */
	public Node(String name, Node nextNode) {
		name_ = name;
		nextNode_ = nextNode;
	}

	public void logPacketPass(Writer report) {
		try {
			report.write("\tNode '");
			report.write(name_);
			report.write("' passes packet on.\n");
			report.flush();
		} catch (IOException exc) {
			// just ignore
		}
	}

	/**
	 * Write a printable representation of #receiver on the given #buf.
	 * <p>
	 * <strong>Precondition:</strong> isInitialized();
	 * </p>
	 * 
	 * @param network
	 *            TODO
	 * @param buf
	 *            TODO
	 */
	public void printOn(Network network, StringBuffer buf) {
		assert network.isInitialized();
		this.printOn(buf, this);
		buf.append(" ... ");
	}

	protected void printOn(StringBuffer buf, Node endNode) {
		buf.append("Node ");
		buf.append(name_);
		buf.append(" [Node]");
		buf.append(" -> ");

		if (endNode != this.nextNode_) {
			this.nextNode_.printOn(buf, endNode);
		}
	}

	/**
	 * Write a HTML representation of #receiver on the given #buf.
	 * <p>
	 * <strong>Precondition:</strong> isInitialized();
	 * </p>
	 * 
	 * @param network
	 *            TODO
	 * @param buf
	 *            TODO
	 */
	public void printHTMLOn(Network network, StringBuffer buf) {
		assert network.isInitialized();
		buf.append("<HTML>\n<HEAD>\n<TITLE>LAN Simulation</TITLE>\n</HEAD>\n<BODY>\n<H1>LAN SIMULATION</H1>");
		buf.append("\n\n<UL>");
		this.printHTMLOn(buf, this);
		buf.append("\n\t<LI>...</LI>\n</UL>\n\n</BODY>\n</HTML>\n");

	}

	protected void printHTMLOn(StringBuffer buf, Node node) {
		buf.append("\n\t<LI> ");
		buf.append("Node ");
		buf.append(name_);
		buf.append(" [Node]");
		buf.append(" </LI>");
		if (node != this.nextNode_) {
			this.nextNode_.printHTMLOn(buf, node);
		}
	}

	/**
	 * Write an XML representation of #receiver on the given #buf.
	 * <p>
	 * <strong>Precondition:</strong> isInitialized();
	 * </p>
	 * 
	 * @param network
	 *            TODO
	 * @param buf
	 *            TODO
	 */
	public void printXMLOn(Network network, StringBuffer buf) {
		assert network.isInitialized();
		buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<network>");
		printXMLOn(buf, this);
		buf.append("\n</network>");
	}

	protected void printXMLOn(StringBuffer buf, Node endNode) {
		buf.append("\n\t");
		buf.append("<node>");
		buf.append(name_);
		buf.append("</node>");
		if (endNode != this.nextNode_) {
			this.nextNode_.printXMLOn(buf, endNode);
		}
	}

}