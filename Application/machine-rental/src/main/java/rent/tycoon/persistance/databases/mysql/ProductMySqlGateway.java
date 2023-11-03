package rent.tycoon.persistance.databases.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rent.tycoon.persistance.converter.CreateProductConverter;
import rent.tycoon.persistance.converter.GetProductConverter;
import rent.tycoon.persistance.converter.UpdateProductConverter;
import rent.tycoon.persistance.databases.entity.ProductJpaMapper;
import rent.tycoon.business.interfaces.repo_interfaces.IProductRepo;
import rent.tycoon.domain.IProduct;
import rent.tycoon.domain.factory.IProductFactory;
import rent.tycoon.persistance.repositories.IProductRepository;

import java.util.List;

@Repository
public class ProductMySqlGateway implements IProductRepo {
    private final IProductRepository repository;
    private final IProductFactory factory;

    @Autowired
    public ProductMySqlGateway(rent.tycoon.persistance.repositories.IProductRepository repository, IProductFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }
    @Transactional
    public long save(IProduct iProduct) {
        ProductJpaMapper productJpaMapper = CreateProductConverter.toProductJpaMapper(iProduct);
        productJpaMapper.getFiles().forEach(files -> files.setProduct(productJpaMapper));
        return repository.save(productJpaMapper).getId();
    }

    @Override
    public boolean existsById(String id) {return repository.existsById(id);}
    @Override
    public boolean existsByName(String name){
        return repository.existsByName(name);
    }
    @Override
    public List<IProduct> findProductByName(String name){
        List <ProductJpaMapper> product = repository.findProductByName(name);
        return GetProductConverter.toProductJpaMapper(product, factory);
    }



    @Transactional
    public IProduct update(IProduct product) {
        long productId = product.getId();

        if (!existsById(String.valueOf(productId))) {
            throw new RuntimeException("Product with Id: " + productId + " doesnt exist");
        }

        ProductJpaMapper existingProduct = repository.findById(String.valueOf(productId)).orElse(null);

        ProductJpaMapper savedProduct = UpdateProductConverter.UpdateExistingProduct(existingProduct);

        ProductJpaMapper newProduct = repository.save(savedProduct);
        return UpdateProductConverter.toProduct(newProduct, factory);
    }
}
