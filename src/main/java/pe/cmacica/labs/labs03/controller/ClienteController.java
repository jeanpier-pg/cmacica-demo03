package pe.cmacica.labs.labs03.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.cmacica.labs.labs03.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController
{
    @GetMapping
    public HttpEntity<List<Cliente>> listar()
    {
        List<Cliente> list = new ArrayList<>();
        for(int i=0; i<=10;i++)
        {
            Cliente c = new Cliente();
            c.setId(i);
            c.setNombres("Nombres" + i);
            list.add(c);
        }

        return ResponseEntity.ok(list);
    }
}
