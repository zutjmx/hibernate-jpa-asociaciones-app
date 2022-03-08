package org.zutjmx.hibernate.asociaciones.app;

import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.entity.Factura;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateAsociacionesManyToOneFind {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Cliente cliente = entityManager.find(Cliente.class,13L);

            entityManager.persist(cliente);

            Factura factura = new Factura("Compra de artículos para oficina",10000L);
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
