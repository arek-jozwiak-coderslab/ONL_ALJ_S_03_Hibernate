package pl.coderslab.controller;

import org.springframework.stereotype.Component;
import pl.coderslab.entity.Product;

import java.util.List;

@Component
public class ProductRepository {


    public List<Product> findAll() {
        return List.of(new Product("Pan Tadeusz", "opis", 10.0, 5),
                new Product("Krzyżacy", "opis", 10.0, 5),
                new Product("W pustyni i w puszczy", "opis", 10.0, 5));
    }
}
