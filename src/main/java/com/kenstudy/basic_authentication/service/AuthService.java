package com.kenstudy.basic_authentication.service;

import com.kenstudy.basic_authentication.entity.Products;
import com.kenstudy.basic_authentication.entity.UserInfo;
import com.kenstudy.basic_authentication.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    List<Products>intProduct(){
        return Arrays.asList(new Products(1,"Vapocool", 20, Arrays.asList("Dancon", "Maximum")),
                new Products(2,"TV Screen", 340, Arrays.asList("LG", "Hisense")),
                new Products(3,"Honda", 31000, Arrays.asList("Pilot", "Hybrid")),
                new Products(4,"Peak Milk", 7, Arrays.asList("DreamVille", "Universe")));
    }

    public List<Products> getAllProducts() {
        return intProduct();
    }

    public Products getProductById(int productId) {
        Optional<Products> product = intProduct().stream().filter(s -> s.getId()==productId)
                .findFirst();

      return product.orElseThrow(() -> new RuntimeException("product not found "+productId));
    }

    public void addUsers(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
    }
}
