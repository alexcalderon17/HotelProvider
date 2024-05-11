package es.deusto.ingenieria.sd.rmi.server.servicios.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.server.dto.ApiResponseList;
import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.HabitacionDTO;

import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiHabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiHabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioAlojamientos;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.deusto.ingenieria.sd.rmi.server.manager.ClienteManager;
import es.deusto.ingenieria.sd.rmi.server.dao.UsuarioDAO;

public class ServicioAlojamientosImpl implements ServicioAlojamientos {

    private static final String HOTEL_PROVIDER_URL = "https://ds2324.arambarri.eus/api";
    private static final String HOTEL_PROVIDER_ALOJAMIENTOS_URL = HOTEL_PROVIDER_URL + "/alojamientos";
    private static final String HOTEL_PROVIDER_HABITACIONES_URL = HOTEL_PROVIDER_URL + "/habitaciones";
    private static final String HOTEL_PROVIDER_API_TOKEN = "0518ee96193abf0dca7b3a46591653eb2b162f3fb2dd6fa681b65b97e3e00243187a1b6839aac73946715fb62719b12a1eb14afc36018935b935c2dbf293448fc98a5cde5a219fc208a3db97489b2c2c479825f212d87658ff3b369e4951b0b3f101ac8d52330262e60846ae80b45b6799c69371e4f47a548053137ada4ec6e5";
    private ObjectMapper objectMapper = new ObjectMapper();
    private ClienteManager cm;

    public ServicioAlojamientosImpl() {
        super();
    }

    private HabitacionDTO convertirApiHabitacionDtoAHabitacionDto(ApiHabitacionDTO apiHabitacionDTO) {
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        habitacionDTO.setNombre(apiHabitacionDTO.getNombre());
        habitacionDTO.setDescripcion(apiHabitacionDTO.getDescripcion());
        habitacionDTO.setAforo(apiHabitacionDTO.getAforo());
        
        return habitacionDTO;
    }
    @Override
    public List<HabitacionDTO> obtenerHabitaciones(int IdAlojamientoSeleccionado)  {
        System.out.println("Obteniendo todas las habitaciones disponibles");
        List<HabitacionDTO> habitaciones = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();  // Asegúrate de que ObjectMapper está instanciado
    
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(HOTEL_PROVIDER_HABITACIONES_URL + "?populate=*")) // Asegúrate de que la URL es correcta
                    .header("Authorization", "Bearer " + HOTEL_PROVIDER_API_TOKEN)
                    .build();
    
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    
            if (response.statusCode() == 200) {
                ApiResponseList<ApiHabitacionDTO> habitacionApiResponse = objectMapper.readValue(response.body(),
                    objectMapper.getTypeFactory().constructParametricType(ApiResponseList.class, ApiHabitacionDTO.class));
    
                for (ApiData<ApiHabitacionDTO> apiData : habitacionApiResponse.getData()) {
                    if (apiData.getAttributes().getAlojamiento().getData().getId() == IdAlojamientoSeleccionado) {
                        HabitacionDTO habitacionDTO = convertirApiHabitacionDtoAHabitacionDto(apiData.getAttributes());
                        habitaciones.add(habitacionDTO);
                    }
                }
            } else {
                System.out.println("Error al obtener las habitaciones. Código de estado: " + response.statusCode());
            }
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    
        return habitaciones;
    }
    
    

    @Override
    public List<AlojamientoDTO> obtenerAlojamientos() {
        System.out.println("obteniendo apartamentos");
        List<AlojamientoDTO> respuesta = new ArrayList<>();

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(HOTEL_PROVIDER_ALOJAMIENTOS_URL))
                    .header("Authorization", "Bearer " + HOTEL_PROVIDER_API_TOKEN)
                    .build();
            HttpResponse<String> response;

            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) { 
                ApiResponseList<AlojamientoDTO> alojamientoApiRespuesta = objectMapper.readValue(response.body(),
                        objectMapper.getTypeFactory().constructParametricType(ApiResponseList.class,
                                AlojamientoDTO.class));
                System.out.println("El primer alojamiento se llama: "
                        + alojamientoApiRespuesta.getData().getFirst().getAttributes().getNombre());
                for (ApiData<AlojamientoDTO> apiData : alojamientoApiRespuesta.getData()) {
                    respuesta.add(apiData.getAttributes());
                }
                return respuesta;
            } else {
                System.out.println("error codigo:" + response.statusCode());
            }
        } catch (IOException | URISyntaxException | InterruptedException e) {
            
            e.printStackTrace();
        }
        return respuesta;
    }
}