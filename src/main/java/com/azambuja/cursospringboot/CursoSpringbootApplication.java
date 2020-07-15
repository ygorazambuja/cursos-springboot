package com.azambuja.cursospringboot;

import com.azambuja.cursospringboot.domain.*;
import com.azambuja.cursospringboot.domain.enums.ClientType;
import com.azambuja.cursospringboot.domain.enums.PaymentState;
import com.azambuja.cursospringboot.repository.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursoSpringbootApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(CursoSpringbootApplication.class, args);
  }

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private StateRepository stateRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private BuyRequestRepository buyRequestRepository;

  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private ItemRequestRepository itemRequestRepository;

  @Override
  public void run(String... args) throws Exception {
    Category category1 = new Category(null, "Informatics");
    Category category2 = new Category(null, "Office");
    Category category3 = new Category(null, "uyg");
    Category category4 = new Category(null, "uygu");
    Category category5 = new Category(null, "Offuyguice");
    Category category6 = new Category(null, "uyg");
    Category category7 = new Category(null, "uyg");
    Category category8 = new Category(null, "Offiuyce");
    Category category9 = new Category(null, "tfytf");
    Category category10 = new Category(null, "Offictfte");
    Category category11 = new Category(null, "Ofrdtrdfice");

    Product product1 = new Product(null, "Computer", 2000.00);
    Product product2 = new Product(null, "Printer", 800.00);
    Product product3 = new Product(null, "Mouse", 80.00);

    category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
    category2.getProducts().addAll(Arrays.asList(product2));

    product1.getCategories().addAll(Arrays.asList(category1));
    product2.getCategories().addAll(Arrays.asList(category1, category2));
    product3.getCategories().addAll(Arrays.asList(category1));

    State state1 = new State(null, "Minas Gerais");
    State state2 = new State(null, "São Paulo");

    City city1 = new City(null, "Uberlândia");
    City city2 = new City(null, "São Paulo");
    City city3 = new City(null, "Campinas");

    state1.getCityList().add(city1);
    state2.getCityList().addAll(Arrays.asList(city2, city3));

    city1.setState(state1);
    city2.setState(state2);
    city3.setState(state2);

    categoryRepository.saveAll(
      Arrays.asList(
        category1,
        category2,
        category3,
        category4,
        category5,
        category6,
        category7,
        category8,
        category9,
        category10,
        category11
      )
    );
    productRepository.saveAll(Arrays.asList(product1, product2, product3));
    stateRepository.saveAll(Arrays.asList(state1, state2));
    cityRepository.saveAll(Arrays.asList(city1, city2, city3));

    Client client1 = new Client(
      null,
      "Maria Silva",
      "maria@gmail.com",
      "36378912377",
      ClientType.FISICPERSON
    );

    client1.getPhones().addAll(Arrays.asList("12312321312", "1231232123"));

    Address address1 = new Address(
      null,
      "Rua Flores",
      "300",
      "Apto 303",
      "Jardim",
      "312312",
      client1,
      city1
    );
    Address address2 = new Address(
      null,
      "Avenida Matos",
      "105",
      "Sala 0800",
      "Centro",
      "123123123",
      client1,
      city2
    );

    client1.getAddressList().addAll(Arrays.asList(address1, address2));
    clientRepository.save(client1);
    addressRepository.saveAll(Arrays.asList(address1, address2));

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    BuyRequest buyRequest = new BuyRequest(
      null,
      simpleDateFormat.parse("30/09/2017 10:32"),
      client1,
      address1
    );
    BuyRequest buyRequest2 = new BuyRequest(
      null,
      simpleDateFormat.parse("10/10/2017 19:35"),
      client1,
      address2
    );

    Payment payment1 = new CardPayment(null, PaymentState.SETTLED, buyRequest, 6);
    buyRequest.setPayment(payment1);

    Payment payment2 = new BilletPayment(
      null,
      PaymentState.PENDING,
      buyRequest2,
      simpleDateFormat.parse("20/10/2017 00:00"),
      null
    );

    buyRequest2.setPayment(payment2);
    client1.getBuyRequests().addAll(Arrays.asList(buyRequest, buyRequest2));

    buyRequestRepository.saveAll(Arrays.asList(buyRequest, buyRequest2));
    paymentRepository.saveAll(Arrays.asList(payment1, payment2));

    ItemRequest itemRequest1 = new ItemRequest(buyRequest, product1, 0.00, 1, 2000.0);
    ItemRequest itemRequest2 = new ItemRequest(buyRequest, product3, 0.00, 2, 80.0);
    ItemRequest itemRequest3 = new ItemRequest(buyRequest2, product2, 100.00, 1, 800.00);

    buyRequest.getItemRequestSet().addAll(Arrays.asList(itemRequest1, itemRequest2));
    buyRequest2.getItemRequestSet().addAll(Arrays.asList(itemRequest3));

    product1.getItemRequestSet().addAll(Arrays.asList(itemRequest1));
    product2.getItemRequestSet().addAll(Arrays.asList(itemRequest3));
    product3.getItemRequestSet().addAll(Arrays.asList(itemRequest2));

    itemRequestRepository.saveAll(
      Arrays.asList(itemRequest1, itemRequest2, itemRequest3)
    );
  }
}
