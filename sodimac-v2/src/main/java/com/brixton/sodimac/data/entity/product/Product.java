package com.brixton.sodimac.data.entity.product;

import com.brixton.sodimac.data.entity.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Getter
@Setter
public class Product extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_category"))
    private Category category;

    private double quantity;

    @Column(name = "pricesupplier")
    private double priceSupplier;
    @Column(name = "pricesale")
    private double priceSale;

    @Column(name = "codeproduct", length = 20)
    private String codeProduct;
    @Transient
    private double minQuantity;
    public void generateCodeProduct() {
        // Suponiendo que tienes un mecanismo para obtener el siguiente número secuencial (e.g., una base de datos)
        int nextSequenceNumber = getNextSequenceNumber();
        String code = name.substring(0, Math.min(3, name.length())).toUpperCase() +
                String.format("%02d%03d", 0, nextSequenceNumber);
        this.codeProduct = code;
    }
    // Método para obtener el siguiente número secuencial (implementación depende de tu arquitectura)
    private static final Map<String, AtomicInteger> sequenceMap = new ConcurrentHashMap<>();
    private int getNextSequenceNumber() {
        String prefix = name.substring(0, Math.min(3, name.length())).toUpperCase();
        AtomicInteger sequence = sequenceMap.computeIfAbsent(prefix, k -> new AtomicInteger(1));
        return sequence.getAndIncrement();
    }
    public static int getMinQuantity(){
        return 50;
    }
}
