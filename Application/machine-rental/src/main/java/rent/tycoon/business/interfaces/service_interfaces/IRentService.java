package rent.tycoon.business.interfaces.service_interfaces;

import rent.tycoon.business.exeption.RentCustomException;
import rent.tycoon.business.model.request.rent.AddRentRowRequestModel;
import rent.tycoon.business.model.request.rent.CreateRentRequestModel;
import rent.tycoon.business.model.response.rent.AddRentRowResponseModel;
import rent.tycoon.business.model.response.rent.CreateRentResponseModel;

public interface IRentService {
    CreateRentResponseModel create(CreateRentRequestModel responseModel) throws RentCustomException;

    Boolean userOwnsRent(long userId, long rentId);

    AddRentRowResponseModel addRentRow(AddRentRowRequestModel requestModel) throws RentCustomException;
}
