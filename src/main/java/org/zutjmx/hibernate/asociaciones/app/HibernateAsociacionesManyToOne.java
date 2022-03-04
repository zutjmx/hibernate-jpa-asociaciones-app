package org.zutjmx.hibernate.asociaciones.app;

import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.entity.Factura;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateAsociacionesManyToOne {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Cliente cliente = new Cliente();
            cliente.setNombre("Aitana");
            cliente.setApellido("Sánchez-Gijón");
            cliente.setFormaPago("Pesetas");

            entityManager.persist(cliente);

            Factura factura = new Factura("Compras de vestuario para Madres Paralelas",25000L);
            factura.setCliente(cliente);
            entityManager.persist(factura);

            System.out.println(factura);
            System.out.println(factura.getCliente());

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
