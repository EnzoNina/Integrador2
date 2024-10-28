package utp.edu.codekion.finanzas.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import utp.edu.codekion.finanzas.model.ResumenTransacciones;
import utp.edu.codekion.finanzas.model.Transacciones;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportePdfGenerate {

    public static byte[] generarReporte(ResumenTransacciones resumen, List<Transacciones> lstTransacciones) {
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            // Crear una nueva página
            PDPage page = new PDPage();
            document.addPage(page);

            // Crear flujo de contenido para la página
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                // Agregar título y detalles del usuario
                contentStream.showText("Reporte de Transacciones");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Usuario: " + resumen.getIdUsuario().getNombres() + " " + resumen.getIdUsuario().getApellidop());
                contentStream.newLine();
                contentStream.showText("Periodo: " + resumen.getPeriodo());
                contentStream.newLine();
                contentStream.newLine();

                // Agregar resumen de ingresos y egresos
                contentStream.showText("Total de Ingresos: $" + resumen.getTotalIngresos());
                contentStream.newLine();
                contentStream.showText("Total de Egresos: $" + resumen.getTotalEgresos());
                contentStream.newLine();
                contentStream.newLine();

                // Agregar detalles de cada transacción
                for (Transacciones transaccion : lstTransacciones) {
                    contentStream.showText("Transacción: " + transaccion.getDescripcion());
                    contentStream.newLine();
                    contentStream.showText("Fecha: " + transaccion.getFechaTransaccion());
                    contentStream.newLine();
                    contentStream.showText("Monto: $" + transaccion.getMonto());
                    contentStream.newLine();
                    contentStream.newLine();
                }

                contentStream.endText();
            }

            // Guardar el documento en el OutputStream
            document.save(baos);

            // Devolver el contenido del PDF como array de bytes
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}