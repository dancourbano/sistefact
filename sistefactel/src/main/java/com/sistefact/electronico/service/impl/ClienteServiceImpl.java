package com.sistefact.electronico.service.impl;


import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sistefact.electronico.dto.ClienteDto;
import com.sistefact.electronico.dto.RandomDto;
import com.sistefact.electronico.models.Cliente;
import com.sistefact.electronico.repository.ClienteRepository;
import com.sistefact.electronico.service.ClienteService;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;

    private final RestTemplate restTemplate;

    public ClienteServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Cliente getById(Long id) {
        return clienteRepo.findById(id).get();
    }    
    @Override
    public Cliente findByNombreAndApellido(String nombre,String apellido) {
        return clienteRepo.findByNombreAndApellido(nombre,apellido);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        
        return clienteRepo.save(cliente);
    }

    @Override
    public void delete(Long id) {
        clienteRepo.deleteById(id);
    }

    @Override
    public List<Cliente> getAll() {
        return (List<Cliente>) clienteRepo.findAll();
    }
   
    @Override
    public Cliente getClienteByDni(String dni) {
    	String uri="https://dniruc.apisperu.com/api/v1/dni/";
    	String token="?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImRhbmllbGNhbS5pbmZAZ21haWwuY29tIn0.VzM5FN1-zMinfqMTmwPY-2J1I9qCft-MyasuYO5LuJ8";
    	
        String concatenadoURL= uri.concat(dni).concat(token);
       
        Cliente cliente=new Cliente();
				
			    
				
			
			    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

			    SSLContext sslContext;
				try {
					sslContext = org.apache.http.ssl.SSLContexts.custom()
					        .loadTrustMaterial(null, acceptingTrustStrategy)
					        .build();
					SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

				    CloseableHttpClient httpClient = HttpClients.custom()
				            .setSSLSocketFactory(csf)
				            .build();

				    HttpComponentsClientHttpRequestFactory requestFactory =
				            new HttpComponentsClientHttpRequestFactory();

				    requestFactory.setHttpClient(httpClient);

				    RestTemplate restTemplate = new RestTemplate(requestFactory);
				    ResponseEntity<ClienteDto> restTemplate1=restTemplate.exchange(concatenadoURL, HttpMethod.GET, null, ClienteDto.class);
				    
				    cliente=convertClienteDtoToCliente(restTemplate1.getBody(),false);
				
				
				
				} catch (KeyManagementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (KeyStoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			    
		
		return cliente;
        
    }
    public Cliente getClienteByRUC(String ruc) {
       String uriConsulta="https://dniruc.apisperu.com/api/v1/ruc/";   	   
   	   RestTemplate restTemplate = new RestTemplate();   	      
	   //Obtener numero RUC
   	   String token="?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImRhbmllbGNhbS5pbmZAZ21haWwuY29tIn0.VzM5FN1-zMinfqMTmwPY-2J1I9qCft-MyasuYO5LuJ8";
	   String consultaFormada=uriConsulta.concat(ruc).concat(token);
	   Cliente cliente=new Cliente();
		
	    
		
		
	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

	    SSLContext sslContext;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom()
			        .loadTrustMaterial(null, acceptingTrustStrategy)
			        .build();
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		    CloseableHttpClient httpClient = HttpClients.custom()
		            .setSSLSocketFactory(csf)
		            .build();

		    HttpComponentsClientHttpRequestFactory requestFactory =
		            new HttpComponentsClientHttpRequestFactory();

		    requestFactory.setHttpClient(httpClient);

		    restTemplate = new RestTemplate(requestFactory);
		    ResponseEntity<ClienteDto> restTemplate1=restTemplate.exchange(consultaFormada, HttpMethod.GET, null, ClienteDto.class);
		     
		    cliente=convertClienteDtoToCliente(restTemplate1.getBody(),true);
	    
	   
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return cliente;
   }
    private Cliente convertClienteDtoToCliente(ClienteDto clienteDto, boolean isRuc) {
    	Cliente clienteNuevo=new Cliente(); 
    	if(isRuc) {
    		clienteNuevo.setApellido("");
    		clienteNuevo.setNombre(clienteDto.getNombreComercial());
    		clienteNuevo.setIdentificador(clienteDto.getRuc());
    		clienteNuevo.setIsRuc(1);
    	}else {
    		clienteNuevo.setApellido(clienteDto.getApellidoPaterno()+" "+clienteDto.getApellidoMaterno());
    		clienteNuevo.setNombre(clienteDto.getNombres());
    		clienteNuevo.setIdentificador(clienteDto.getDni());
    		clienteNuevo.setIsRuc(0);
    	}
    	return clienteNuevo;
    }
    private Cliente setDataRuc(ClienteDto clienteDto,String identificador) {
    	Cliente cliente=new Cliente();
    	cliente.setIsRuc(1);
    	cliente.setNombre(clienteDto.getRazon_social());
    	cliente.setDireccion(clienteDto.getDomicilio_fiscal());
    	cliente.setIdentificador(identificador);
    	cliente.setStatus(1);
    	return cliente;
    }
    @Override
    public List<Cliente> getCustomersByUser(Long userId) {
        return clienteRepo.findByUser_Id(userId);
    }
}
