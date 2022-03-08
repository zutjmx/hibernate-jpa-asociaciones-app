package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.entity.ClienteDetalle;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateAsociacionesOneToOneFind {
    public static void main(String[] args) {
        Faker faker = new Faker();
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Cliente cliente = entityManager.find(Cliente.class,20L);

            ClienteDetalle clienteDetalle = new ClienteDetalle(true,6500L);
            entityManager.persist(clienteDetalle);

            cliente.setClienteDetalle(clienteDetalle);
            entityManager.getTransaction().commit();

            System.out.println(cliente);
            System.out.println(clienteDetalle);

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }
}
