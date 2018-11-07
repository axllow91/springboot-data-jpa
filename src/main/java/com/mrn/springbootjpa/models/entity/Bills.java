package com.mrn.springbootjpa.models.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bills")
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String observation;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createAt;

    @ManyToOne
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private List<ItemBill> itemBills;

    public Bills() {
        itemBills = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ItemBill> getItemBills() {
        return itemBills;
    }

    public void setItemBills(List<ItemBill> itemBills) {
        this.itemBills = itemBills;
    }

    public Double getTotal() {
        Double total = 0.0;
        int size = itemBills.size();
        for (int i = 0; i < size; i++) {
            total += itemBills.get(i).calculateAmount();
        }
        return total;
    }
}
