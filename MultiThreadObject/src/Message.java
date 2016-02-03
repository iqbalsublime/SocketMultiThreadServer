import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -5276243156244245870L;
	String hash;
	String range;
	String text;
	
	public Message(){
		
	}
	public Message(String text) {
		this.text = text;
	}
	
	public Message(String hash, String range) {
		this.hash = hash;
		this.range = range;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	
	
}
