package com.Employee.Helper;

import com.Employee.Model.ApiResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

  public void generate(List<ApiResponse> listofEmployees, HttpServletResponse response)
      throws DocumentException, IOException {

    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, response.getOutputStream());
    document.open();

    Font fontTiltle = FontFactory.getFont(FontFactory.COURIER);
    fontTiltle.setSize(20);

    Paragraph paragraph1 = new Paragraph("List of Employees", fontTiltle);
    paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
    document.add(paragraph1);

    PdfPTable table = new PdfPTable(8);
    table.setWidthPercentage(100f);

    table.setWidths(new int[] {3, 3, 3, 3, 3, 3, 3, 3});
    table.setSpacingBefore(5);

    PdfPCell cell = new PdfPCell();
    cell.setBackgroundColor(CMYKColor.YELLOW);
    cell.setPadding(5);

    Font font = FontFactory.getFont(FontFactory.COURIER);
    font.setColor(CMYKColor.BLACK);

    cell.setPhrase(new Phrase("ID", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Name", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Email", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Department", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Role", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Location", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Active", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Photo", font));
    table.addCell(cell);

    for (ApiResponse emp : listofEmployees) {
      table.addCell(String.valueOf(emp.getEmployee().getEmpId()));
      table.addCell(emp.getEmployee().getFullName());
      table.addCell(emp.getEmployee().getEmail());
      table.addCell(String.valueOf(emp.getEmployee().getDeptId()));
      table.addCell(emp.getEmployee().getEmpRole());
      table.addCell(emp.getEmployee().getLocation());
      table.addCell(String.valueOf(emp.getEmployee().isActive()));
      try {
        byte[] photo = emp.getEmployee().getPhoto();
        PdfPCell pCell = new PdfPCell();
        if (photo != null && photo.length > 0) {
          Image img = Image.getInstance(photo);

          float maxSize = 50f;
          img.scaleToFit(maxSize, maxSize);
          pCell.addElement(img);
        } else {
          PdfPCell emptyPhotoCell = new PdfPCell();
          table.addCell(emptyPhotoCell);
        }
        table.addCell(pCell);
      } catch (IOException | RuntimeException e) {
        e.printStackTrace();
        System.out.println("Erooroooror:" + e.getMessage());
      }
    }
    document.add(table);
    document.close();
  }
}
