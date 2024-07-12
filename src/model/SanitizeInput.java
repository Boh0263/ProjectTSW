package model;

public class SanitizeInput {
	
	public static String sanitize(String input) {
		if (input == null) {
			return null;
		}
    
		StringBuilder sanitized = new StringBuilder();
		for (char c : input.toCharArray()) {
			switch (c) {
            	case '<':
            		sanitized.append("&lt;");
            		break;
            	case '>':
            		sanitized.append("&gt;");
            		break;
            	case '&':
            		sanitized.append("&amp;");
            		break;
            	case '"':
            		sanitized.append("&quot;");
            		break;
            	case '\'':
            		sanitized.append("&#x27;");
            		break;
            	case '/':
            		sanitized.append("&#x2F;");
            		break;
            	default:
            		sanitized.append(c);
            		break;
			}
		}
		return sanitized.toString();
	}

}
