import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QRCodeGenerator {

    // Method to generate QR Code
    private static void generateQRCode(String data, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 350, 350);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("QR Code generated: " + filePath);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Map to store student information
        Map<Integer, String> studentData = new HashMap<>();

        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Input student information
        for (int i = 1; i <= numberOfStudents; i++) {
            System.out.println("Enter details for student " + i + " (e.g., Name, ID, Class): ");
            String studentInfo = scanner.nextLine();
            studentData.put(i, studentInfo);
        }

        // Ask for a specific student number
        System.out.print("Enter the student number to generate QR Code for: ");
        int studentNumber = scanner.nextInt();

        // Check if the student number is valid
        if (studentData.containsKey(studentNumber)) {
            String studentInfo = studentData.get(studentNumber);
            String filePath = "Student_" + studentNumber + "_QRCode.png";

            try {
                generateQRCode(studentInfo, filePath);
            } catch (WriterException | IOException e) {
                System.out.println("Error while generating QR Code: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid student number entered!");
        }

        scanner.close();
    }
}


