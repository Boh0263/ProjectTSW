package control;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.util.*;
import java.util.concurrent.*;


@WebFilter("/upload")
public class FileUploadFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private static final int MAX_REQ_PER_MIN = 10;
	private static final Map<String, byte[]> MAGIC_NUMBERS = new HashMap<String, byte[]>();
	
	private ConcurrentHashMap<String, ConcurrentLinkedQueue<Long>> requests = new ConcurrentHashMap<String, ConcurrentLinkedQueue<Long>>();
      		
			static {
				
				MAGIC_NUMBERS.put("image/jpeg", new byte[] { (byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0 });
				MAGIC_NUMBERS.put("image/png", new byte[] { (byte) 0x89, (byte) 0x50,  (byte) 0x4E, (byte) 0x47 });
				MAGIC_NUMBERS.put("image/gif", new byte[] { (byte) 0x47, (byte) 0x49, (byte) 0x46, (byte) 0x38 });

			}
   
    public FileUploadFilter() {
        super();
 
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String IpAddress = req.getRemoteAddr();
		long currentTime = Instant.now().toEpochMilli();
		
		requests.putIfAbsent(IpAddress, new ConcurrentLinkedQueue<Long>());
		ConcurrentLinkedQueue<Long> queue = requests.get(IpAddress);
		
		while (!queue.isEmpty() && currentTime - queue.peek() > 60000) {
			queue.poll();
		}
		
		if (requests.size() < MAX_REQ_PER_MIN) {
			queue.add(currentTime);
			
			Part filePart = req.getPart("file");
			String contentType = filePart.getContentType();
			InputStream filestream = filePart.getInputStream();
			
			if (!MAGIC_NUMBERS.containsKey(contentType)) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				res.getWriter().write("Formato file non supportato");
				return;
			}
			
			byte[] MagicNumber = MAGIC_NUMBERS.get(contentType);
			byte[] currentMagicNumber = new byte[MagicNumber.length];
			
			filestream.read(MagicNumber, 0, currentMagicNumber.length);
			
			if (!isMagicNumberValid(MagicNumber, currentMagicNumber)) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				res.getWriter().write("Il formato del file non coincide con l'estensione del file");
				return;
			}
			
			chain.doFilter(request, response);
			
		} else {
			res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
			res.getWriter().write("Too many requests");
			return;
		}
		
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		super.init(fConfig);
	}
	
	private boolean isMagicNumberValid(byte[] atteso, byte[] vero) {
		if (atteso.length != vero.length) {
			return false;
		}
		for (int i = 0; i < atteso.length; i++) {
			if (atteso[i] != vero[i]) {
				return false;
			}
		}
		return true;
	}
}
