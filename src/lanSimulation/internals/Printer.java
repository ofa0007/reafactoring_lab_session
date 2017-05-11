package lanSimulation.internals;

public class Printer extends Node {

	public Printer(String name) {
		super(name);
	}

	@Override
	protected void printXMLOn(StringBuffer buf, Node endNode) {
		buf.append("\n\t");
		buf.append("<printer>");
		buf.append(name_);
		buf.append("</printer>");
		if (endNode != this.nextNode_) {
			this.nextNode_.printXMLOn(buf, endNode);
		}
	}

	@Override
	protected void printHTMLOn(StringBuffer buf, Node endNode) {
		buf.append("\n\t<LI> ");
		buf.append("Printer ");
		buf.append(name_);
		buf.append(" [Printer]");
		buf.append(" </LI>");
		if (endNode != this.nextNode_) {
			this.nextNode_.printHTMLOn(buf, endNode);
		}
	}

	@Override
	protected void printOn(StringBuffer buf, Node endNode) {
		buf.append("Printer ");
		buf.append(name_);
		buf.append(" [Printer]");
		buf.append(" -> ");
		if (endNode != this.nextNode_) {
			this.nextNode_.printOn(buf, endNode);
		}
	}

}
