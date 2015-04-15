package br.gov.sp.fatec.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "aluguel")
public class Aluguel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @Cascade({CascadeType.PERSIST})
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne()
    @Cascade({CascadeType.PERSIST})
    @JoinColumn(name = "id_carro")
    private Carro carro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

}
