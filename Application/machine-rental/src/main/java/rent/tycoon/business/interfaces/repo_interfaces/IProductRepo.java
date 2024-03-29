package rent.tycoon.business.interfaces.repo_interfaces;

import rent.tycoon.domain.Category;
import rent.tycoon.domain.IProduct;

import java.util.List;

public interface IProductRepo extends IProductExistsGateway{
    long save(IProduct product);
    List<IProduct> findProductByName(String name);
    IProduct update(IProduct product);

    IProduct getProductById(Long id);
    List <IProduct> getMachineByCategory (Integer categoryId);

    List<IProduct> getAllProducts();
    List<IProduct> filterProduct(String name, int price, long category);
}
