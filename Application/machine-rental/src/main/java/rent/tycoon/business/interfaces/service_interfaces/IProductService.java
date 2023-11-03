package rent.tycoon.business.interfaces.service_interfaces;

import rent.tycoon.business.exeption.ProductCustomException;
import rent.tycoon.business.model.request.product.CreateProductRequestModel;
import rent.tycoon.business.model.request.product.UpdateProductRequestModel;
import rent.tycoon.business.model.response.CreateProductResponseModel;
import rent.tycoon.business.model.response.GetProductResponseModel;
import rent.tycoon.business.model.response.UpdateProductResponseModel;

public interface IProductService {
    CreateProductResponseModel create(CreateProductRequestModel requestModel) throws ProductCustomException;

    GetProductResponseModel findProductByName(String name) throws ProductCustomException;
    UpdateProductResponseModel update(UpdateProductRequestModel requestModel) throws ProductCustomException;
}