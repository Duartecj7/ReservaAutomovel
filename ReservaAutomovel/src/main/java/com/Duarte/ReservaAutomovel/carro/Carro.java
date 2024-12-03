package com.Duarte.ReservaAutomovel.carro;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
public class Carro {

    @Id
    @GeneratedValue
    private UUID id;

    private String modelo;
    private String marca;
    private Integer ano;
    private Boolean disponivel = true;

    public Carro() {
    }

    public UUID getId() {
        return this.id;
    }

    public String getModelo() {
        return this.modelo;
    }

    public String getMarca() {
        return this.marca;
    }

    public Integer getAno() {
        return this.ano;
    }

    public Boolean getDisponivel() {
        return this.disponivel;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Carro)) return false;
        final Carro other = (Carro) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$modelo = this.getModelo();
        final Object other$modelo = other.getModelo();
        if (this$modelo == null ? other$modelo != null : !this$modelo.equals(other$modelo)) return false;
        final Object this$marca = this.getMarca();
        final Object other$marca = other.getMarca();
        if (this$marca == null ? other$marca != null : !this$marca.equals(other$marca)) return false;
        final Object this$ano = this.getAno();
        final Object other$ano = other.getAno();
        if (this$ano == null ? other$ano != null : !this$ano.equals(other$ano)) return false;
        final Object this$disponivel = this.getDisponivel();
        final Object other$disponivel = other.getDisponivel();
        if (this$disponivel == null ? other$disponivel != null : !this$disponivel.equals(other$disponivel))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Carro;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $modelo = this.getModelo();
        result = result * PRIME + ($modelo == null ? 43 : $modelo.hashCode());
        final Object $marca = this.getMarca();
        result = result * PRIME + ($marca == null ? 43 : $marca.hashCode());
        final Object $ano = this.getAno();
        result = result * PRIME + ($ano == null ? 43 : $ano.hashCode());
        final Object $disponivel = this.getDisponivel();
        result = result * PRIME + ($disponivel == null ? 43 : $disponivel.hashCode());
        return result;
    }

    public String toString() {
        return "Carro(id=" + this.getId() + ", modelo=" + this.getModelo() + ", marca=" + this.getMarca() + ", ano=" + this.getAno() + ", disponivel=" + this.getDisponivel() + ")";
    }
}
