package com.azambuja.cursospringboot.services.validation;

import com.azambuja.cursospringboot.domain.Client;
import com.azambuja.cursospringboot.domain.enums.ClientType;
import com.azambuja.cursospringboot.dto.ClientNewDTO;
import com.azambuja.cursospringboot.repository.ClientRepository;
import com.azambuja.cursospringboot.resources.exceptions.FieldMessage;
import com.azambuja.cursospringboot.services.validation.utils.BR;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientInsertValidator
  implements ConstraintValidator<ClientInsert, ClientNewDTO> {
  @Autowired
  private ClientRepository clientRepository;

  @Override
  public void initialize(ClientInsert ann) {}

  @Override
  public boolean isValid(ClientNewDTO clientNewDTO, ConstraintValidatorContext context) {
    List<FieldMessage> list = new ArrayList<>();

    if (
      clientNewDTO.getClientType().equals(ClientType.FISICPERSON.getCode()) &&
      !BR.isValidCPF(clientNewDTO.getCpfOrCnpj())
    ) {
      list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
    }

    if (
      clientNewDTO.getClientType().equals(ClientType.JURIDICPERSON.getCode()) &&
      !BR.isValidCNPJ(clientNewDTO.getCpfOrCnpj())
    ) {
      list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));
    }

    Client aux = clientRepository.findByEmail(clientNewDTO.getEmail());
    if (aux != null) {
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
