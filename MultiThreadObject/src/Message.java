import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -5276243156244245870L;
	String hash;
	String range;
	String text;
	String name;
	
	public Message(){
		
	}
	public Message(String name) {
		this.name = name;
	}
	
	public Message(String hash, String range, String name, String text) {
		this.hash = hash;
		this.range = range;
		this.name = name;
		this.text = text;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
