package es.deusto.ingenieria.sd.rmi.server.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import es.deusto.ingenieria.sd.rmi.server.exceptions.ErrorCreacionQr;
import es.deusto.ingenieria.sd.rmi.server.jdo.Reserva;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioPuertas;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioReserva;

public class ServicioPuertasImpl implements ServicioPuertas {

    ServicioReserva servicioReserva;

    public ServicioPuertasImpl() {
        this.servicioReserva = new ServicioReservaImpl();
    }

    @Override
    public byte[] abrirPuerta(String codigoReserva) {
        Reserva reserva;
        try {
            reserva = servicioReserva.leerReserva(codigoReserva);
        } catch (Exception e) {
            System.out.println("No se ha encontrado reserva con este codigo de reserva");
            return null;
        }
        String qrData = reserva.getCodigoReserva() + "_" + reserva.getCliente().getCorreo();
        try{
            return generateQRCode(qrData);
        }catch(IOException e){
            throw new ErrorCreacionQr("Error al crear codigo qr", e); 
        }
    }

    public static byte[] generateQRCode(String data) throws IOException {
        // Construct the URL for the API call
        String apiUrl = "https://api.qrserver.com/v1/create-qr-code/?data=" + data;
        URL url = new URL(apiUrl);

        // Open a connection to the API
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Check the response code
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to get QR code. HTTP response code: " + responseCode);
        }

        // Read the response into a byte array
        try (InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

}
