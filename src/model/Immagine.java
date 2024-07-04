package model;

public class Immagine {
    private String Placeholder;
    private byte[] Content;
    
    
	public Immagine(String Placeholder, byte[] Content) {
		this.Placeholder = Placeholder;
		this.Content = Content;
	}


	public String getPlaceholder() {
		return Placeholder;
	}


	public void setPlaceholder(String placeholder) {
		Placeholder = placeholder;
	}


	public byte[] getContent() {
		return Content;
	}


	public void setContent(byte[] content) {
		Content = content;
	}
	
}
