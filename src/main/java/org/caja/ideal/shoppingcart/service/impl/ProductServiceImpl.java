package org.caja.ideal.shoppingcart.service.impl;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.caja.ideal.shoppingcart.exeptions.InvalidProductExeptions;
import org.caja.ideal.shoppingcart.exeptions.NotFoundProductExeptions;
import org.caja.ideal.shoppingcart.models.Product;
import org.caja.ideal.shoppingcart.repository.IProductRepository;
import org.caja.ideal.shoppingcart.service.ProductService;
import org.caja.ideal.shoppingcart.utils.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Log4j2
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private IProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProduct() {
        log.info(" LISTADO DE PRODUCTO ");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProduct(@NonNull Long id) {
        log.info(" PRODUCTO ");
        return repository.findById(id).orElseThrow(() -> new NotFoundProductExeptions(Message.NOT_FOUND_PRODUCT));
    }

    @Transactional
    @Override
    public Product saveProduct(Product body) {
      Optional<Product> productId = repository.findByName(body.getName());
      if(productId.isPresent()){
          throw new InvalidProductExeptions(Message.PRODUCT_ALREADY_EXISTS);
      }
        log.info("SAVE");
        return repository.save(body);
    }
    @Transactional
    @Override
    public Product updateProduct(@NonNull Long id, @NonNull Product body) {
        Product product = repository.findById(id).orElseThrow(( ) -> new NotFoundProductExeptions(Message.NOT_FOUND_PRODUCT));
        BeanUtils.copyProperties(body, product, "id", "createAt");
        Product updateProduct = product;
        return repository.save(updateProduct);
    }

    @Transactional
    @Override
    public void deleteProduct(@NonNull Long id) {
        Objects.requireNonNull(id, "Product body must not be null");
        Product product = repository.findById(id).orElseThrow(() -> new NotFoundProductExeptions(Message.NOT_FOUND_PRODUCT));
        repository.delete(product);
    }
}
