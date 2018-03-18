package application.service;

import application.report.PDFReport;
import application.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReportService {

    @Autowired
    private BookRepo bookRepo;

    public void generateReport() throws IOException {
        PDFReport report = new PDFReport();
        report.generateRaport(bookRepo.findAll());
    }
}
