package pl.michal.Shop.DTOs;

import com.sun.xml.bind.v2.TODO;
import pl.michal.Shop.model.Product;

public class CreateOrderLineRequest {
//TODO Wykorzystaj ta klase zeby brac zamowienia od usera- ale tak zeby nie mala order id- pokombinuj z konverterami i create order zeby wszystko dzialalo

  //  private Long orderID;
    private Long productId;

    private Long quantity;

    public CreateOrderLineRequest(Long productId, Long quantity) {
   //     this.orderID = orderID;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CreateOrderLineRequest() {
    }

//       public Long getOrderID() {
//         return orderID;
//      }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }
}
