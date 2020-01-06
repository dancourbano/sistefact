package com.sistefact.electronico.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sistefact.electronico.dto.ClienteDto;
import com.sistefact.electronico.dto.RandomDto;
import com.sistefact.electronico.models.Cliente;
import com.sistefact.electronico.repository.ClienteRepository;
import com.sistefact.electronico.service.ClienteService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;



    @Override
    public Cliente getById(Long id) {
        return clienteRepo.getOne(id);
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
    	String uri="http://aplicaciones007.jne.gob.pe/srop_publico/Consulta/api/AfiliadoApi/GetNombresCiudadano";
    	RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();  
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Content-Type", "application/json;chartset=utf-8");
        headers.add("Requestverificationtoken", "30OB7qfO2MmL2Kcr1z4S0ttQcQpxH9pDUlZnkJPVgUhZOGBuSbGU4qM83JcSu7DZpZw-IIIfaDZgZ4vDbwE5-L9EPoBIHOOC1aSPi4FS_Sc1:clDOiaq7mKcLTK9YBVGt2R3spEU8LhtXEe_n5VG5VLPfG9UkAQfjL_WT9ZDmCCqtJypoTD26ikncynlMn8fPz_F_Y88WFufli38cUM-24PE1");
        ClienteDto clienteDto=new ClienteDto();
        clienteDto.setCODDNI(dni);
        HttpEntity<ClienteDto> entity = new HttpEntity<ClienteDto>(clienteDto, headers);
        ClienteDto result = restTemplate.postForObject( uri, entity, ClienteDto.class);
        Cliente cliente=separateDataRest(result.getData());
        return cliente;
    }
    public Cliente getClienteByRUC(String ruc) {
       String uriConsulta="http://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/jcrS00Alias";
   	   String uriRandom="http://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/captcha?accion=random";
   	   RestTemplate restTemplate = new RestTemplate();
   	   //Obtener numero Random
	   RandomDto randomDto=new RandomDto();	   
	   ResponseEntity<RandomDto> cadena = restTemplate.getForEntity( uriRandom, RandomDto.class);
	   System.out.println(cadena);
	   //Obtener numero RUC
	   HttpHeaders headers = new HttpHeaders();  
	   headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	   headers.add("Content-Type", "application/json;chartset=utf-8");
	   ResponseEntity<ClienteDto> clienteDto = restTemplate.getForEntity( uriRandom, ClienteDto.class);
	   Cliente cliente=new Cliente();
       return cliente;
   }
    private Cliente separateDataRest(String data) {
    	Cliente clienteNuevo=new Cliente();
    	String[] listadoData=data.split("[|]");    	
    	clienteNuevo.setApellido(listadoData[0]+" "+listadoData[1]);
    	clienteNuevo.setNombre(listadoData[2]);
    	return clienteNuevo;
    }
}
