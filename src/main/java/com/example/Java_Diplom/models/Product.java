package com.example.Java_Diplom.models;


import javax.persistence.*;

@Entity
@Table(name = "Product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

@Column(name = "name")
    private String name;

    @Column(name="price")
private Integer price;

    @Column(name = "oldprice")
    private int oldprice;


    @Column(name="image")
    private String image;

@Column(name = "discription")
    private String discription ;

@ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="type_id",referencedColumnName = "id")
ProductType producttype;

    public ProductType getProductType() {
        return producttype;
    }

    public void setProductType(ProductType productType) {
        this.producttype = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOldprice() {
        return oldprice;
    }

    public void setOldprice(int oldprice) {
        this.oldprice = oldprice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
