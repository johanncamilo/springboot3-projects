package com.johann.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johann.springmvc.model.User;
import com.johann.springmvc.model.dto.ParamDto;
import com.johann.springmvc.model.dto.UserDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    // * VALORES INYECTADOS desde values.properties con @Value

    @Value("${config.username}")
    private String username;

    @Value("${config.password}")
    private String password;

    @Value("${config.listOfValues}")
    private List<String> listOfValues;

    @Value("${config.code}")
    private String code;

    /**
     * * SpEL (Spring Expression Language)
     * Pemite evaluar expresiones dinámicas dentro del contexto de Spring
     * (beans, propiedades, colecciones, llamadas a métodos)
     * transforma valores en tiempo de arranque
     * todo lo que quiero manipular va dentro de gato y llaves dentro de doble comillas: "#{}" 
     * y las otras expresiones y valores se anidan con '' sencillas
     * ? ESTE EJEMPLO ES UNA MANIPULACIÓN SEMÁNTICA MANUAL DE INYECCIÓN DE UNA LISTA
     * EN EL CONTROLLER
     * \\s* regex para sanitizar espacios, los omite
     */
    @Value("#{'${config.listOfValues}'.toUpperCase().split(',\\s*')}")
    private List<String> SpELmanualValueList;

    // otro uso de SpEL
    @Value("#{'${config.listOfValues}'.toUpperCase()}")
    private String valueString;

    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.product}")
    private String product;

    @Value("#{${config.valuesMap}.price}")
    private Long price;


    // ** ENDPOINTS **

    @GetMapping("una/{message}")
    public ParamDto getBaz(@PathVariable(name = "message") String messageOtro) {

        ParamDto param = new ParamDto();
        param.setMessage(messageOtro);
        return param;

    }

    @GetMapping("varias/{product}/{id}")
    public Map<String, Object> getMix(@PathVariable String product, @PathVariable Long id) {

        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);

        return json;
    }

    @PostMapping
    public UserDto create(@RequestBody User user) {
        UserDto dto = new UserDto();
        dto.setFullname(user.getName() + " " + user.getLastname());
        dto.setTitle("Usuario con correo: " + user.getEmail());
        return dto;
    }

    // * INYECCIÓN DE @Value como parametro
    @GetMapping("tagvalues")
    public Map<String, Object> getTagValues(@Value("${config.message}") String message) {

        Map<String, Object> json = new HashMap<>();
        json.put("username", username);
        json.put("code", code);
        json.put("message", message);
        // json.put("message2", environment.getProperty("config.message"));
        // json.put("code2", code2);
        json.put("listOfValues", listOfValues);
        json.put("valueString", valueString);
        json.put("SpELmanualValueList", SpELmanualValueList);
        json.put("valueMap", valuesMap);
        json.put("product", product);
        json.put("price", price);
        return json;

    }

}
