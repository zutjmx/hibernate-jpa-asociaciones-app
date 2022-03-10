package org.zutjmx.hibernate.asociaciones.app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;

    @Column(name = "forma_pago")
    private String formaPago;

    @Embedded
    private Auditoria auditoria = new Auditoria();

    @OneToMany(/*fetch = FetchType.EAGER,*/
            cascade = CascadeType.ALL,orphanRemoval = true
    )
    //@JoinColumn(name = "id_cliente")
    @JoinTable(name = "tbl_clientes_direcciones",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_direccion"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_direccion"}))
    private List<Direccion> direcciones;

    @OneToMany(/*fetch = FetchType.EAGER,*/
            cascade = CascadeType.ALL,orphanRemoval = true,
            mappedBy = "cliente"
    )
    private List<Factura> facturas;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "cliente")
    private ClienteDetalle clienteDetalle;

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public Cliente() {
        facturas = new ArrayList<>();
        direcciones = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }

    public ClienteDetalle getClienteDetalle() {
        return clienteDetalle;
    }

    public void setClienteDetalle(ClienteDetalle clienteDetalle) {
        this.clienteDetalle = clienteDetalle;
    }

    public void addDetalle(ClienteDetalle clienteDetalle) {
        this.clienteDetalle = clienteDetalle;
        clienteDetalle.setCliente(this);
    }

    public void removeDetalle() {
        clienteDetalle.setCliente(null);
        this.clienteDetalle = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Cliente addFactura(Factura factura) {
        this.facturas.add(factura);
        factura.setCliente(this);
        return this;
    }

    public void removeFactura(Factura facturaAEliminar) {
        this.facturas.remove(facturaAEliminar);
        facturaAEliminar.setCliente(null);
    }

    @Override
    public String toString() {

        LocalDateTime creado = this.auditoria != null? auditoria.getCreadoEn() : null;
        LocalDateTime editado = this.auditoria != null? auditoria.getEditadoEn() : null;

        return "Datos :: Cliente {" +
                "id = " + id +
                ", nombre = '" + nombre + '\'' +
                ", apellido = '" + apellido + '\'' +
                ", formaPago = '" + formaPago + '\'' +
                ", creadoEn = '" + creado + '\'' +
                ", editadoEn = '" + editado + '\'' +
                ", direcciones = '" + direcciones + '\'' +
                ", facturas = '" + facturas + '\'' +
                ", detalle = '" + clienteDetalle + '\'' +
                '}';
    }

}
