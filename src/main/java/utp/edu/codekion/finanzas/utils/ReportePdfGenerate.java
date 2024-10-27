package utp.edu.codekion.finanzas.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import utp.edu.codekion.finanzas.model.ResumenTransacciones;
import utp.edu.codekion.finanzas.model.Transacciones;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportePdfGenerate {

    public static byte[] generarReporte(ResumenTransacciones resumen, List<Transacciones> lstTransacciones) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // Crea un nuevo documento de iText
            Document document = new Document();

            // Usa PdfWriter para asociar el documento al OutputStream
            PdfWriter.getInstance(document, baos);

            // Abre el documento
            document.open();

            // Agrega detalles del usuario
            document.add(new Paragraph("Reporte de Transacciones"));
            document.add(new Paragraph("Usuario: " + resumen.getIdUsuario().getNombres() + " " + resumen.getIdUsuario().getApellidop()));
            document.add(new Paragraph("Periodo: " + resumen.getPeriodo()));

            // Agrega un resumen de ingresos y egresos
            document.add(new Paragraph("Total de Ingresos: $" + resumen.getTotalIngresos()));
            document.add(new Paragraph("Total de Egresos: $" + resumen.getTotalEgresos()));

            // Agrega detalles de cada transacción
            for (Transacciones transaccion : lstTransacciones) {
                document.add(new Paragraph("Transacción: " + transaccion.getDescripcion()));
                document.add(new Paragraph("Fecha: " + transaccion.getFechaTransaccion()));
                document.add(new Paragraph("Monto: $" + transaccion.getMonto()));
            }

            // Cierra el documento
            document.close();

            // Devuelve el contenido del PDF como un array de bytes
            return baos.toByteArray();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}