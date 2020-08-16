package ua.training.spring.hometask.view;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import ua.training.spring.hometask.domain.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@Component("ticketPdfView")
public class TicketPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Collection<Ticket> ticketData = (Collection<Ticket>) model.get("ticketData");
        Table table = new Table(4);
        table.addCell("Event name");
        table.addCell("purchased by");
        table.addCell("ticket seat");
        table.addCell("price");

        for (Ticket ticket : ticketData) {
            table.addCell(ticket.getEvent().getName());
            table.addCell(Objects.nonNull(ticket.getUser()) ? ticket.getUser().getEmail() : StringUtils.EMPTY);
            table.addCell(String.valueOf(ticket.getSeat()));
            table.addCell(String.valueOf(ticket.getBasePrice()));
        }

        document.add(table);
    }
}
