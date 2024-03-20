package dashbah.shkaf.mapper;

import dashbah.shkaf.dto.ProductDTO;
import dashbah.shkaf.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

//    @Mapping(source = "dto.id", target = "id")
//    @Mapping(source = "dto.title", target = "title")
//    @Mapping(source = "dto.price", target = "price")
    Product ProductDTOtoProduct(ProductDTO dto);

    @InheritInverseConfiguration
//    @Mapping(source = "product.id", target = "id")
//    @Mapping(source = "product.title", target = "title")
//    @Mapping(source = "product.price", target = "price")
    ProductDTO productToProductDTO(Product product);

    List<Product> toProductList(List<ProductDTO> productDTOS);

    List<ProductDTO> fromProductList(List<Product> products);
}
