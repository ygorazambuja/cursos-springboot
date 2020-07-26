package com.azambuja.cursospringboot.services.validation;

import com.azambuja.cursospringboot.domain.Client;
import com.azambuja.cursospringboot.dto.ClientDTO;
import com.azambuja.cursospringboot.repository.ClientRepository;
import com.azambuja.cursospringboot.resources.exceptions.FieldMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClientUpdateValidator
  implements ConstraintValidator<ClientUpdate, ClientDTO> {
  @Autowired
  private HttpServletRequest request;

  @Autowired
  private ClientRepository clientRepository;

  @Override
  public void initialize(ClientUpdate ann) {}

  @Override
  public boolean isValid(ClientDTO value, ConstraintValidatorContext context) {
    Map<String, String> map = (Map<String, String>) request.getAttribute(
      HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE
    );
    Integer uriId = Integer.parseInt(map.get("id"));

    List<FieldMessage> list = new ArrayList<>();

    Client aux = clientRepository.findByEmail(value.getEmail());
    if (aux != null && !aux.getId().equals(uriId)) {
      list.add(new FieldMessage("Email", "Already exist email"));
    }

    for (FieldMessage fieldMessage : list) {
      context.disableDefaultConstraintViolation();
      context
        .buildConstraintViolationWithTemplate(fieldMessage.getMessage())
        .addPropertyNode(fieldMessage.getFieldName())
        .addConstraintViolation();
    }

    return list.isEmpty();
  }
}
