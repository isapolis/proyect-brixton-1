package com.brixton.sodimac.service;

import com.brixton.sodimac.controller.manageexception.GenericNotFoundException;
import com.brixton.sodimac.data.entity.product.Category;
import com.brixton.sodimac.data.entity.product.Product;
import com.brixton.sodimac.data.enums.RegistryStateType;
import com.brixton.sodimac.data.repository.CategoryRepository;
import com.brixton.sodimac.data.repository.ProductRepository;
import com.brixton.sodimac.dto.request.CreateProductRequestDTO;
import com.brixton.sodimac.dto.response.ProductResponseDTO;
import com.brixton.sodimac.service.mapper.ProductMapper;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
@ToString
public class ProductServiceImpl implements ProductService{
    private static final String USER_APP = "BRIXTON";
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO){
        Product newProduct = ProductMapper.INSTANCE.createProductRequestDTOToProduct(createProductRequestDTO);
        newProduct.setCreatedAt(LocalDateTime.now());
        newProduct.setCreatedBy(USER_APP);
        newProduct.setRegistryState(RegistryStateType.ACTIVE);
        Category category = categoryRepository.findById(newProduct.getCategory().getId()).orElseThrow(()-> new GenericNotFoundException("Id de category no existente"));
        newProduct.getCategory().setName(category.getName());
        newProduct.generateCodeProduct();
        newProduct.setCodeProduct(newProduct.getCodeProduct());
        newProduct.setMinQuantity(Product.getMinQuantity());
        productRepository.save(newProduct);
        ProductResponseDTO productResponseDTO = ProductMapper.INSTANCE.productToCreateProductResponseDTO(newProduct);
        return productResponseDTO;

    }

    

    /*
    private static final String USER_APP = "BRIXTON";
    Map<Long, Product> products = new HashMap<>();
    Map<Long, Product> productsForBuys=new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();

    public ProductServiceImpl(){
        module.addDeserializer(LocalDate.class, new CustomDateDeserializer());
        module.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString(localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        });


        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(module);
        objectMapper.findAndRegisterModules();
    }


    @Override
    public Object createProduct(ProductRequestDTO inputProduct) {
        try {
            String jsonInput = objectMapper.writeValueAsString(inputProduct);

            Product product =  objectMapper.readValue(jsonInput, Product.class);
            product.setId(UUID.randomUUID().getMostSignificantBits());
            product.setCreatedAt(LocalDateTime.now());
            product.setCreatedBy(USER_APP);
            product.setStatus(TypeStatusForAudit.ACTIVE);
            products.put(product.getId(), product);

            String jsonOutput = objectMapper.writeValueAsString(product);
            ProductResponseDTO output = objectMapper.readValue(jsonOutput, ProductResponseDTO.class);
            return output;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Object> createWithList(List<ProductRequestDTO> inputProducts) {

        List<Object> outputProducts =  new ArrayList<>();
        for(ProductRequestDTO product : inputProducts){
            try {
                String jsonInput = objectMapper.writeValueAsString(product);
                Product productTemporal = objectMapper.readValue(jsonInput, Product.class);
                productTemporal.setId(UUID.randomUUID().getMostSignificantBits());
                productTemporal.setCreatedAt(LocalDateTime.now());
                productTemporal.setCreatedBy(USER_APP);
                productTemporal.setStatus(TypeStatusForAudit.ACTIVE);
                products.put(productTemporal.getId(), productTemporal);

                String jsonOutput = objectMapper.writeValueAsString(productTemporal);
                ProductResponseDTO output = objectMapper.readValue(jsonOutput, ProductResponseDTO.class);
                outputProducts.add(output);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return outputProducts;
    }

    @Override
    public Object updateProduct(long id, ProductResponseDTO product) {

        try {
            String jsonInput = objectMapper.writeValueAsString(product);
            Product productTemporal = objectMapper.readValue(jsonInput, Product.class);

            Product original = products.get(id);
            if(original != null){
                original.setName(productTemporal.getName());
                original.setCategory(productTemporal.getCategory());
                original.setQuantity(productTemporal.getQuantity());
                original.setMinQuantity(productTemporal.getMinQuantity());
                original.setPriceSupplier(productTemporal.getPriceSupplier());
                original.setPriceSale(productTemporal.getPriceSale());
                original.setCodeProduct(productTemporal.getCodeProduct());
                original.setUpdatedAt(LocalDateTime.now());
                original.setUpdatedBy(USER_APP);
                original.setStatus(TypeStatusForAudit.ACTIVE);

                String jsonOutput = objectMapper.writeValueAsString(original);
                ProductResponseDTO output = objectMapper.readValue(jsonOutput, ProductResponseDTO.class);
                return output;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Object getProduct(long id) {
        Product product = products.get(id);
        if (product != null) {
            try {

                String jsonOutput = objectMapper.writeValueAsString(product);
                ProductResponseDTO output = objectMapper.readValue(jsonOutput, ProductResponseDTO.class);
                return output;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return null;
    }
    @Override
    public Product getProductBusiness(long id) {
        return products.get(id);

    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product original = products.get(id);
        if(original != null) {
            original.setName(product.getName());
            original.setCategory(product.getCategory());
            original.setQuantity(product.getQuantity());
            original.setMinQuantity(product.getMinQuantity());
            original.setPriceSupplier(product.getPriceSupplier());
            original.setPriceSale(product.getPriceSale());
            original.setCodeProduct(product.getCodeProduct());
            original.setUpdatedAt(LocalDateTime.now());
            original.setUpdatedBy(USER_APP);
            original.setStatus(TypeStatusForAudit.ACTIVE);
            products.put(original.getId(), original);
            if(original.getQuantity() <= original.getMinQuantity()){
              productsForBuys.put(original.getId(), original);
            }
            return original;
        }
            return null;
    }

    @Override
    public List<Object> getActiveProducts() {

        List<Object> activeProducts = new ArrayList<>();
        for(Product product: products.values()){
            if (product != null && product.getStatus() == TypeStatusForAudit.ACTIVE) {
                try {
                    String jsonOutput = objectMapper.writeValueAsString(product);
                    ProductResponseDTO output = objectMapper.readValue(jsonOutput, ProductResponseDTO.class);
                    activeProducts.add(output);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return activeProducts;
    }


    @Override
    public List<Object> getListProduct(TypeCategory category) {
        List<Object> listProducts = new ArrayList<>();
        try {
            for (Product product : products.values()) {
                if (product.getCategory().getName() == category && product.getStatus() == TypeStatusForAudit.ACTIVE) {
                    String jsonOutput = objectMapper.writeValueAsString(product);
                    ProductResponseDTO output = objectMapper.readValue(jsonOutput, ProductResponseDTO.class);
                    listProducts.add(output);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProducts;
    }


    @Override
    public Object deleteProduct(long id) {
        Product product = products.get(id);
        if(product != null) {
            if(product.getStatus() == TypeStatusForAudit.INACTIVE){
                return null;
            }
            product.setStatus(TypeStatusForAudit.INACTIVE);
            product.setUpdatedAt(LocalDateTime.now());
            product.setUpdatedBy(USER_APP);
            return product;
        }
        return null;

    }

    @Override
    public Object buyProduct(long id, double quantityPurchased){
        try {
            Product original = products.get(id);
            if(original != null){
                original.setQuantity(original.getQuantity()+quantityPurchased);
                original.setUpdatedAt(LocalDateTime.now());
                products.put(original.getId(), original);
                productsForBuys.remove(original.getId());
                String jsonOutput = objectMapper.writeValueAsString(original);
                ProductResponseDTO output = objectMapper.readValue(jsonOutput, ProductResponseDTO.class);
                return output;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Object sellProduct(long id, int quantitySold){
        try {
            Product original = products.get(id);
            if(original != null){
                original.setQuantity(original.getQuantity()-quantitySold);
                original.setUpdatedAt(LocalDateTime.now());
                products.put(original.getId(), original);
                String jsonOutput = objectMapper.writeValueAsString(original);
                ProductResponseDTO output = objectMapper.readValue(jsonOutput, ProductResponseDTO.class);
                return output;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    @Override
    public List<Product> getListProductForBuy(){//List de productos por comprar
        List<Product> listProducts = new ArrayList<>();
        for(Product productTemporal: productsForBuys.values()){
            listProducts.add(productTemporal);
        }
        return listProducts;
    }

    @Override
    public Product getProductForBuyBusiness(long idProduct) {
        Product productTemp = productsForBuys.get(idProduct);
        if (productTemp!=null){
            return productTemp;
        }
        return null;
    }
*/

}
