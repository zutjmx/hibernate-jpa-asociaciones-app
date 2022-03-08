package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.entity.ClienteDetalle;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateAsociacionesOneToOne {
    public static void main(String[] args) {
        Faker faker = new Faker();
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Cliente cliente = new Cliente(faker.name().firstName(),faker.name().lastName());
            cliente.setFormaPago(faker.currency().name());

            entityManager.persist(cliente);

            ClienteDetalle clienteDetalle = new ClienteDetalle(true,6500L);
            entityManager.persist(clienteDetalle);

            cliente.setClienteDetalle(clienteDetalle);
            entityManager.getTransaction().commit();

            System.out.println(cliente);
            System.out.println(cliente.getClienteDetalle());
            System.out.println(clienteDetalle);

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }
}
