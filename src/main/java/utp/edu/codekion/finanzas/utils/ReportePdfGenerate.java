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

    private static final float MARGIN = 50;
    private static final float LINE_HEIGHT = 14.5f;
    private static final float START_Y = 750;
    private static final float PAGE_HEIGHT = 792; // Altura estándar de una página A4 en puntos.

    public static byte[] generarReporte(ResumenTransacciones resumen, List<Transacciones> lstTransacciones) {
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            // Crear una nueva página y un flujo de contenido
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Configuración inicial del flujo de contenido
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.setLeading(LINE_HEIGHT);
            float yPosition = START_Y;
            contentStream.beginText();
            contentStream.newLineAtOffset(MARGIN, yPosition);

            // Agregar título y detalles del usuario
            contentStream.showText("Reporte de Transacciones");
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Usuario: " + resumen.getIdUsuario().getNombres() + " "
                    + resumen.getIdUsuario().getApellidop());
            contentStream.newLine();
            contentStream.showText("Periodo: " + resumen.getPeriodo());
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Total de Ingresos: $" + resumen.getTotalIngresos());
            contentStream.newLine();
            contentStream.showText("Total de Egresos: $" + resumen.getTotalEgresos());
            contentStream.newLine();
            contentStream.newLine();

            // Agregar detalles de cada transacción
            for (Transacciones transaccion : lstTransacciones) {
                if (yPosition - LINE_HEIGHT * 4 < MARGIN) {
                    // Terminar el flujo de contenido actual y crear una nueva página
                    contentStream.endText();
                    contentStream.close();

                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.setLeading(LINE_HEIGHT);
                    yPosition = START_Y;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(MARGIN, yPosition);
                }

                contentStream.showText("Transacción: " + transaccion.getDescripcion());
                contentStream.newLine();
                contentStream.showText("Fecha: " + transaccion.getFechaTransaccion());
                contentStream.newLine();
                contentStream.showText("Monto: $" + transaccion.getMonto());
                contentStream.newLine();
                contentStream.newLine();
                yPosition -= LINE_HEIGHT * 4; // Descontar la altura utilizada
            }

            contentStream.endText();
            contentStream.close();

            // Guardar el documento en el OutputStream
            document.save(baos);

            // Devolver el contenido del PDF como array de bytes
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error generating PDF report", e);
        }
    }
}
