package application.report;

import application.model.Book;
import application.repository.BookRepo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class PDFReport
{

       @Autowired
        private BookRepo bookRepo;

        private static String FILE = "E:\\Facultate\\SD\\Pr\\Report.pdf";
        private static Font font = new Font("TimesRoman", Font.PLAIN, 20);

        private static void addEmptyLine(Paragraph paragraph, int number)
        {
            for (int i = 0; i < number; i++)
            {
                paragraph.add(new Paragraph(" "));
            }
        }
        private void addTitlePage(Document document) throws DocumentException {
            Paragraph preface = new Paragraph();
            preface.add("Report");
            addEmptyLine(preface,2);
            preface.add(new Paragraph());
            document.add(preface);
        }

        private void addContent(Document document, List<Book> books)throws DocumentException
        {
            //table
            PdfPTable table = new PdfPTable(3);

            PdfPCell c1 = new PdfPCell(new Phrase("Title"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Author"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Genre"));
            table.addCell(c1);

            for(int i=0; i<books.size(); i++)
            {
                table.addCell(books.get(i).getTitle());
                table.addCell(books.get(i).getAuthor());
                table.addCell(books.get(i).getGenre());
            }
            document.add(table);
        }

        private void addMetaData(Document document)
        {
            document.addTitle("PDF Report");
            document.addAuthor("Ionel Carmen");
            document.addCreator("Ionel Carmen");
        }

        public void generateRaport(List<Book> books) throws IOException
        {
            try
            {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(FILE));
                document.open();
                addMetaData(document);
                addTitlePage(document);
                addContent(document,books);
                document.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
}
