package org.cypmaster.timers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.cypmaster.dao.StudentDAO;

import javax.ejb.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Ciprian at 12/16/2017
 */

@Singleton
@LocalBean
@Startup
public class TimerSessionBean {

    @EJB
    private StudentDAO studentDAO;

    private final static String PDF_REPORT_PATH = ".";
    private final static Font CAT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private final static Font SUB_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);

    //    @Schedule(second = "13,34,57", minute = "*", hour = "*")
    public void execute(Timer timer) {
        System.out.println("Executing ...");
        System.out.println("Execution Time : " + new Date());
        try {
            writePRFReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Generated Report");
        System.out.println("____________________________________________");
    }

    private void writePRFReport() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String date = sdf.format(new Date());
        String pathToReport = String.format("%s/student-preference-%s.pdf", PDF_REPORT_PATH, date);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pathToReport));
        document.open();

        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Students Project preference", CAT_FONT));

        Anchor anchor = new Anchor("Students Project Preference", CAT_FONT);
        anchor.setName("Students Project Preference");
        Chapter tableChapter = new Chapter(new Paragraph(anchor), 1);
        Paragraph subPara = new Paragraph("", SUB_FONT);
        Section tableSection = tableChapter.addSection(subPara);
        createTable(tableSection);
        document.newPage();
        document.add(tableSection);
        document.close();
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void createTable(Section section) {

        List<String> studentsProjects = studentDAO.findProjectWithStudentPreference();

        PdfPTable table = new PdfPTable(2);
        PdfPCell projectNameColumn = new PdfPCell(new Phrase("Project Name"));
        projectNameColumn.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(projectNameColumn);

        PdfPCell projectPreferenceColumn = new PdfPCell(new Phrase("Student preference"));
        projectPreferenceColumn.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(projectPreferenceColumn);

        studentsProjects.forEach(info -> {
            String[] data = info.split(" ", 2);
            table.addCell(data[0]);
            table.addCell(data[1]);
        });
        section.add(table);
    }

}
