package com.elitsoft.proyectoCuestionario_backend.repositories;


import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.entities.ProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductVersionRepository extends JpaRepository<ProductVersion, Long> {

    @Query("SELECT v FROM ProductVersion v WHERE v.id <= 48 AND v.product.id = :productId")
    List<ProductVersion> findOfficialVersionsByProduct(@Param("productId") Long productId);

    @Query("SELECT v FROM ProductVersion v WHERE v.name = :vrsName AND v.product = :producto")
    Optional<ProductVersion> findByVrsNameAndPrd(@Param("vrsName") String vrsName, @Param("producto") Product product);

}
