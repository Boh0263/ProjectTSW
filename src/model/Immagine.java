package model;

public class Immagine {
    private String Placeholder;
    private byte[] Content;
    private MimeType mime_type;
    
    
	public Immagine(String Placeholder, byte[] Content, MimeType mime_type) {
		this.Placeholder = Placeholder;
		this.Content = Content;
		this.mime_type = mime_type;
	}
	
	public Immagine(String Placeholder, byte[] Content, String mime_type) {
		this.Placeholder = Placeholder;
		this.Content = Content;
		if (mime_type == null) {
			this.mime_type = MimeType.UNSUPPORTED;
			return;
		}
		this.mime_type = MimeType.getEnum(mime_type);
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
	
	public MimeType getMimeType() {
		return mime_type;
	}
	
	public void setMimeType(MimeType mime_type) {
		this.mime_type = mime_type;
	}
	
}
