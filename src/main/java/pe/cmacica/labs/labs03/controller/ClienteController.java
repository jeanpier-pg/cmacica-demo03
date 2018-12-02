package pe.cmacica.labs.labs03.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.cmacica.labs.labs03.dominio.Cliente;
import pe.cmacica.labs.labs03.service.ClienteService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController
{

    @Autowired
    private ClienteService clienteservice;

    @GetMapping
    public HttpEntity<List<Cliente>> listar()
    {
        //List<Cliente> list = new ArrayList<>();
        //for(int i=0; i<=10;i++)
        //{
        //    Cliente c = new Cliente();
        //    c.setId(i);
        //    c.setNombres("Nombres" + i);
        //    list.add(c);
        //}

        List<Cliente> list = clienteservice.listar();
        return ResponseEntity.ok(list);
    }

    @GetMapping ("/{id}")
    public HttpEntity<Cliente> listar (@PathVariable("id") int id)
    {
        if (id == 5)
        {
            return ResponseEntity.notFound().build();
        }

        //Cliente c = new Cliente();
        //c.setId(id);
        //c.setNombres("Jean Pier");

        //return ResponseEntity.ok(c);

        Cliente c = clienteservice.getCliente(id);
        return ResponseEntity.ok(c);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);
    @PostMapping
    public HttpEntity<String> guardar(@RequestBody Cliente cliente)
    {
        if (StringUtils.isBlank(cliente.getNombres()))
        {
            return ResponseEntity.badRequest().build();
        }
        //if (cliente.getNombres() == "")
        //{
        //    return ResponseEntity.badRequest().build();
        //}
        LOGGER.debug("{}",cliente.getId());
        LOGGER.debug("{}",cliente.getNombres());
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<String> actualizar(@PathVariable("id") int id, @RequestBody Cliente cliente)
    {
        if(id==0)
        {
            return ResponseEntity.badRequest().build();
        }
        LOGGER.debug("UPDATE");
        LOGGER.debug("{}",cliente.getId());
        LOGGER.debug("{}",cliente.getNombres());

        cliente.setId(id);
        clienteservice.actualizar(cliente);
        //return ResponseEntity.accepted().build();
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public HttpEntity<String> borrar(@PathVariable("id") int id)
    {
        if(id==0)
        {
            return ResponseEntity.badRequest().build();
        }
        LOGGER.debug("DELETE");
        return ResponseEntity.accepted().build();
    }
}
