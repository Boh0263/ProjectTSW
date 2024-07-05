package model;

public enum MimeType {
JPEG("image/jpeg"),
PNG("image/png"),
GIF("image/gif"), 
UNSUPPORTED("unsupported");
	
	private String string;

MimeType(String string) {
	this.string = string;
}

public String getMimeType() {
	return string;
}

static MimeType getEnum(String mime_type) {
	
	switch (mime_type) {
	case "image/jpeg":
		return JPEG;
	case "image/png":
		return PNG;
	case "image/gif":
		return GIF;
	default:
		return UNSUPPORTED;
	}
	
	
}

}
