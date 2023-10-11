package rent.tycoon.Controller.Converters;

import org.springframework.stereotype.Component;
import rent.tycoon.Controller.CreateRentRequest;
import rent.tycoon.Controller.CreateRentResponse;
import rent.tycoon.domain.Rent;
import rent.tycoon.domain.dto.CreateRentDTO;
@Component
public class CreateRentConverter {

    public static CreateRentDTO toCreateRent(CreateRentRequest request){
        return CreateRentDTO.builder()
                .productId(request.getProductId())
                .customerId(request.getCustomerId())
                .start(request.getStart())
                .end(request.getEnd())
                .paid(request.getPaid())
                .discount(request.getDiscount())
                .address(request.getAddress())
                .city(request.getCity())
                .timestamp(request.getTimestamp())
                .total(request.getTotal())
                .build();
    }

    public static CreateRentResponse responseConverter(Rent rent){
        CreateRentResponse response = new CreateRentResponse();
        response.setId(response.getId());
        return response;
    }

}
