package control;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/PDF")
public class GeneraPDFControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrdineDAOImp odao = new OrdineDAOImp();
	UserDAOImp udao = new UserDAOImp();
	

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ID = SanitizeInput.sanitize(request.getParameter("ID"));
        String xml = null;
        
        HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");
		
		if (username != null && role.equalsIgnoreCase("R")) {
			try {
			 Utente user = udao.doRetrieveByName(username);
			 
			 Collection<Ordine> ordini = odao.doRetrieveByUser(user);
			 
			 
			 for(Ordine o : ordini) {
                 if(o.getID() == Integer.parseInt(ID)) {
                     xml = odao.doRetrieveFattura(o);
                        break;
                 }
             }
			 
			}
			catch (SQLException e) {
                throw new ServletException("Errore: " + e.getMessage()); 
            }

        if (xml == null || xml.isEmpty()) {
        	System.out.println(xml);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "XML parameter is missing.");
            return;
        }

        try {
            // Create PDF from XML
            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            generatePdfFromXml(xml, pdfOutputStream);

            // Send PDF as response
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"order-"+ ID + ".pdf\"");
            response.setContentLength(pdfOutputStream.size());
            response.getOutputStream().write(pdfOutputStream.toByteArray());
        } catch (Exception e) {
            throw new ServletException("Error generating PDF", e);
        }
	} else if (username != null && role.equalsIgnoreCase("A") /*SI PRESUME CHE l'admin sia stato verificato nel filtro*/) {
		try {
			
			Collection<Ordine> ordini = odao.doretrieveAll(null);

			for (Ordine o : ordini) {
				if (o.getID() == Integer.parseInt(ID)) {
					xml = odao.doRetrieveFattura(o);
					break;
				}
			}

		} catch (SQLException e) {
			throw new ServletException("Errore: " + e.getMessage());
		}

		if (xml == null || xml.isEmpty()) {
			System.out.println(xml);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "XML parameter is missing.");
			return;
		}

		try {
			// Create PDF from XML
			ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
			generatePdfFromXml(xml, pdfOutputStream);

			// Send PDF as response
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"order-" + ID + ".pdf\"");
			response.setContentLength(pdfOutputStream.size());
			response.getOutputStream().write(pdfOutputStream.toByteArray());
		} catch (Exception e) {
			throw new ServletException("Error generating PDF", e);
		}
	}
	}

    private void generatePdfFromXml(String xml, ByteArrayOutputStream outputStream) throws Exception {
        
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(new StringReader(xml));

        
        try (PDDocument pdfDocument = new PDDocument()) {
            PDPage page = new PDPage();
            pdfDocument.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page)) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 750);

                
                writeOrderDetails(document, contentStream);

                contentStream.endText();
            }

            pdfDocument.save(outputStream);
        }
    }

    private void writeOrderDetails(Document document, PDPageContentStream contentStream) throws IOException {
        Element rootElement = document.getRootElement();

        contentStream.showText("Dettagli Ordine:");
        contentStream.newLine();
 

        

      
        contentStream.newLine();
        contentStream.showText("Prodotti:");
        contentStream.newLine();

        Element productsElement = rootElement.getChild("Prodotti");
        if (productsElement != null) {
            for (Element productElement : productsElement.getChildren("Prodotto")) {
                contentStream.showText("Nome: " + productElement.getChildText("Nome"));
                contentStream.newLine();
                contentStream.showText("Qta: " + productElement.getChildText("Quantita"));
                contentStream.newLine();
                contentStream.showText("Imponibile: " + productElement.getChildText("Imponibile") + " €");
                contentStream.newLine();
                if (productElement.getChildText("IVA") != null && !productElement.getChildText("IVA").equals("null"))
                contentStream.showText("IVA: " + productElement.getChildText("IVA") + "%");
                else contentStream.showText("IVA: 0%");
                contentStream.newLine();
            }
        }
        contentStream.newLine();
        contentStream.showText("ID Ordine: " + rootElement.getChildText("ID"));
        contentStream.newLine();
        contentStream.showText("Data: " + rootElement.getChildText("Data_Ordine"));
        contentStream.newLine();
        contentStream.showText("Cliente: " + rootElement.getChildText("Ragione_Sociale"));
        contentStream.newLine();
        contentStream.showText("Indirizzo di spedizione: " + rootElement.getChildText("Indirizzo"));
        contentStream.newLine();
        contentStream.showText("Sconto: " + rootElement.getChildText("Sconto") + " €");
        contentStream.newLine();
        contentStream.showText("Stato: " + rootElement.getChildText("Stato"));
        contentStream.newLine();
        contentStream.showText("Prezzo Finale: " + rootElement.getChildText("Prezzo_Totale") + " €");
        contentStream.newLine();
    }
}