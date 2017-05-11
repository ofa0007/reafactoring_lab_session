package lanSimulation.internals;

public class WorkStation extends Node {

	public WorkStation(String name) {
		super(name);
	}

	@Override
	protected void printXMLOn(StringBuffer buf, Node endNode) {
		buf.append("\n\t");
		buf.append("<workstation>");
		buf.append(name_);
		buf.append("</workstation>");
		
		if(endNode!=this.nextNode_){
			this.nextNode_.printXMLOn(buf, endNode);
		}
	}
	
	@Override
	protected void printHTMLOn(StringBuffer buf, Node endNode) {
		buf.append("\n\t<LI> ");
		buf.append("Workstation ");
		buf.append(name_);
		buf.append(" [Workstation]");
		buf.append(" </LI>");
		
		if(endNode!=this.nextNode_){
			this.nextNode_.printHTMLOn(buf, endNode);
		}
	}
	
	@Override
	protected void printOn(StringBuffer buf, Node endNode) {
		buf.append("Workstation ");
		buf.append(name_);
		buf.append(" [Workstation]");
		buf.append(" -> ");
		if(endNode!=this.nextNode_){
			this.nextNode_.printOn(buf, endNode);
		}
	}

}
