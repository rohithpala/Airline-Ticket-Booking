//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//
//public class JavaPdfHelloWorld {
//    public static void main(String[] args) {
//        Document document = new Document();
//        try {
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Hello World"));
//            document.open();
//            document.add(new Paragraph());
//            document.close();
//            writer.close();
//        } catch (DocumentException | FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}