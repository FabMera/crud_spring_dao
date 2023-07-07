package com.springboot.datajpa.app.controllers;

import com.springboot.datajpa.app.models.dao.IClienteDao;
import com.springboot.datajpa.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;

    @RequestMapping (value = "/listar",method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteDao.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form")
    //Otra forma de hacerlo con map.
    public String crear(Map<String, Object> model) {
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de cliente");
        return "form";
    }
    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result,Model model){
        if(result.hasErrors()){
            //AddAttribute es para pasar datos a la vista,recibe parametros como el nombre del atributo y el valor.
            model.addAttribute("titulo", "Formulario de cliente");
            return "form";
        }
        clienteDao.save(cliente);
        return "redirect:listar";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar (@PathVariable(value = "id") Long id, Map<String, Object> model){
        Cliente cliente = null;
        if(id > 0){
            cliente = clienteDao.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar cliente");
        return "form";
    }

}
