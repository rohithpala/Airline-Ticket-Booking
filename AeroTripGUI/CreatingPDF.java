//package AeroTripGUI;
//
//import AeroTripGUI.*;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//
//public class CreatingPDF {
//    public static void main(String[] args) throws FileNotFoundException, DocumentException {
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream("R:\\MiniProjects\\JavaProject\\ticket.pdf"));
//
//        //open the document
//        document.open();
//
//        //creating table
//        float[] columnWidths = {};
//        PdfPTable table = new PdfPTable(columnWidths);
//        table.addCell(new PdfPCell(new Paragraph("Flight Name")));
//        table.addCell(new PdfPCell(new Paragraph("From")));
//        table.addCell(new PdfPCell(new Paragraph("To")));
//        table.addCell(new PdfPCell(new Paragraph("Passenger Name")));
//        table.addCell(new PdfPCell(new Paragraph("Phone No.")));
//        table.addCell(new PdfPCell(new Paragraph("Adults")));
//        table.addCell(new PdfPCell(new Paragraph("Children")));
//        table.addCell(new PdfPCell(new Paragraph("Infants")));
//        table.addCell(new PdfPCell(new Paragraph("Cost")));
//        table.addCell(new PdfPCell(new Paragraph("Duration")));
//
//        table.addCell(new PdfPCell(new Paragraph()));
//        table.addCell(new PdfPCell(new Paragraph("From")));
//        table.addCell(new PdfPCell(new Paragraph("To")));
//        table.addCell(new PdfPCell(new Paragraph("Passenger Name")));
//        table.addCell(new PdfPCell(new Paragraph("Phone No.")));
//        table.addCell(new PdfPCell(new Paragraph("Adults")));
//        table.addCell(new PdfPCell(new Paragraph("Children")));
//        table.addCell(new PdfPCell(new Paragraph("Infants")));
//        table.addCell(new PdfPCell(new Paragraph("Cost")));
//        table.addCell(new PdfPCell(new Paragraph("Duration")));
//
//    }
//}
