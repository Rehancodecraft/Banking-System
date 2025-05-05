package Bank;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneratePDF {
	public static void printTransactionHistory(ResultSet resultSet, String accountNo, String accountHolderName) {
		try {
			String folderName = "Transaction History";
			File folder = new File(folderName);
			if (!folder.exists()) {
				folder.mkdir();
			}
			
			// Define the file path inside the folder
			String filePath =
					folderName + File.separator + "Transaction-History(" + accountNo + ").pdf";
			
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			
			Font titleFont = new Font(Font.HELVETICA, 25, Font.BOLD);
			Paragraph heading = new Paragraph("Transaction History\n", titleFont);
			heading.setAlignment(Element.ALIGN_CENTER);
			document.add(heading);
			
			Font infoHeadingFont = new Font(Font.HELVETICA, 16, Font.BOLD);
			Paragraph info =
					new Paragraph(
							"Account Holder: "
									+ accountHolderName
									+ "               Account No: "
									+ accountNo
									+ "\n\n",
							infoHeadingFont);
			info.setAlignment(Element.ALIGN_CENTER);
			document.add(info);
			
			PdfPTable table = new PdfPTable(6); // 6 columns
			table.setWidthPercentage(110);
			table.setWidths(new float[] {5, 5, 5, 5, 5, 5});
			
			Font headerFont = new Font(Font.HELVETICA, 10, Font.BOLD);
			
			String[] headers = {
					"Transaction-Type", "Amount", "Receiver-Account",
					"Receiver-Name", "Description", "Date & Time"
			};
			
			for (String title : headers) {
				PdfPCell headerCell = new PdfPCell(new Phrase(title, headerFont));
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(headerCell);
			}
			
			try {
				Font rowFont = new Font(Font.HELVETICA, 10);
				while (resultSet.next()) {
					String type = resultSet.getString("Transaction_Type");
					double amount = resultSet.getDouble("Amount");
					String receiverAccountNo = resultSet.getString("Receiver_Account_No");
					String receiverName = resultSet.getString("Receiver_Name"); // might be null
					String description = resultSet.getString("Description");
					String time = resultSet.getString("Time");
					
					PdfPCell cell;
					
					cell = new PdfPCell(new Phrase(type, rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(String.valueOf(amount), rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
					
					cell =
							new PdfPCell(
									new Phrase(receiverAccountNo != null ? receiverAccountNo : "Null", rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(receiverName != null ? receiverName : "Null", rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(description, rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(time, rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
				}
				document.add(table);
				document.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	public static void printAllAccounts(ResultSet resultSet) {
		try {
			String folderName = "Accounts";
			File folder = new File(folderName);
			if (!folder.exists()) {
				folder.mkdir(); // Create folder if it doesn't exist
			}
			
			String filePath = folderName + File.separator + "All Accounts.pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			
			Font titleFont = new Font(Font.HELVETICA, 25, Font.BOLD);
			Paragraph heading = new Paragraph("Details of All Accounts\n\n", titleFont);
			heading.setAlignment(Element.ALIGN_CENTER);
			document.add(heading);
			
			PdfPTable table = new PdfPTable(5); // 5 columns
			table.setWidthPercentage(100); // Adjusted width
			table.setWidths(new float[]{5, 5, 5, 5, 5});
			
			Font headerFont = new Font(Font.HELVETICA, 10, Font.BOLD);
			String[] headers = {
					"Account-No", "Name", "Account-Type", "Balance", "Date-of-Creation"
			};
			
			// Add headers
			for (String title : headers) {
				PdfPCell headerCell = new PdfPCell(new Phrase(title, headerFont));
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(headerCell);
			}
			
			try {
				Font rowFont = new Font(Font.HELVETICA, 10);
				boolean dataFound = false;
				while (resultSet.next()) {
					dataFound = true;
					String accountNo = resultSet.getString("Account_No");
					String name = resultSet.getString("AccountHolder_Name");
					String accountType = resultSet.getString("Account_Type");
					double balance = resultSet.getDouble("Balance");
					String dateOfCreation = resultSet.getString("CreationDate");
					
					// Add data to rows
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(accountNo, rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(name, rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(accountType, rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(String.valueOf(balance), rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(dateOfCreation, rowFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
				}
				
				// If no data is found
				if (!dataFound) {
					document.add(new Paragraph("No data found to display."));
				}
				
				// Add table to document and close
				document.add(table);
				document.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
}
