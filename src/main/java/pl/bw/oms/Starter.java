package pl.bw.oms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bw.oms.domain.model.Client;
import pl.bw.oms.domain.model.Product;
import pl.bw.oms.domain.model.ProductCategory;
import pl.bw.oms.domain.model.Supplier;
import pl.bw.oms.domain.repository.CategoryRepository;
import pl.bw.oms.domain.repository.ClientRepository;
import pl.bw.oms.domain.repository.ProductRepository;
import pl.bw.oms.domain.repository.SupplierRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class Starter implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;


    public Starter(ClientRepository clientRepository, ProductRepository productRepository,
                   SupplierRepository supplierRepository, CategoryRepository categoryRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("elo 1");

        //insert client
        Client client3 = new Client("Leon", "Niemczyk", "01-123", "Warszawa", "Krucza 6A/12", "515-516-515", "leon@niemczyk.pl","klient z apki");
        clientRepository.save(client3);

        //insert product
        Optional<Supplier> supp = supplierRepository.findById(2L);
        Supplier supplierFromDB = supp.orElse(null);
        Optional<ProductCategory> prodCat = categoryRepository.findById(3L);
        ProductCategory productCategoryFromDB = prodCat.orElse(null);
        Product prod4 = new Product("Stół Max4", new BigDecimal(210), new BigDecimal(300), "stół kuchenny", supplierFromDB, productCategoryFromDB);
        productRepository.save(prod4);

        System.out.println("elo 2");

    }
}
