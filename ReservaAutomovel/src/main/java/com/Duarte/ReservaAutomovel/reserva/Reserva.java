package com.Duarte.ReservaAutomovel.reserva;

import com.Duarte.ReservaAutomovel.carro.Carro;
import com.Duarte.ReservaAutomovel.cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
@Data
@Entity
public class Reserva {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Carro carro;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Reserva() {
    }


    public UUID getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public Carro getCarro() {
        return this.carro;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public LocalDate getDatafim() {
        return this.dataFim;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDatafim(LocalDate datafim) {
        this.dataFim = datafim;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Reserva)) return false;
        final Reserva other = (Reserva) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$cliente = this.getCliente();
        final Object other$cliente = other.getCliente();
        if (this$cliente == null ? other$cliente != null : !this$cliente.equals(other$cliente)) return false;
        final Object this$carro = this.getCarro();
        final Object other$carro = other.getCarro();
        if (this$carro == null ? other$carro != null : !this$carro.equals(other$carro)) return false;
        final Object this$dataInicio = this.getDataInicio();
        final Object other$dataInicio = other.getDataInicio();
        if (this$dataInicio == null ? other$dataInicio != null : !this$dataInicio.equals(other$dataInicio))
            return false;
        final Object this$datafim = this.getDatafim();
        final Object other$datafim = other.getDatafim();
        if (this$datafim == null ? other$datafim != null : !this$datafim.equals(other$datafim)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Reserva;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $cliente = this.getCliente();
        result = result * PRIME + ($cliente == null ? 43 : $cliente.hashCode());
        final Object $carro = this.getCarro();
        result = result * PRIME + ($carro == null ? 43 : $carro.hashCode());
        final Object $dataInicio = this.getDataInicio();
        result = result * PRIME + ($dataInicio == null ? 43 : $dataInicio.hashCode());
        final Object $datafim = this.getDatafim();
        result = result * PRIME + ($datafim == null ? 43 : $datafim.hashCode());
        return result;
    }

    public String toString() {
        return "Reserva(id=" + this.getId() + ", cliente=" + this.getCliente() + ", carro=" + this.getCarro() + ", dataInicio=" + this.getDataInicio() + ", datafim=" + this.getDatafim() + ")";
    }
}
