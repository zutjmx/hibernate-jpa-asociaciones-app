package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.entity.Direccion;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {
        Faker faker = new Faker();
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Cliente cliente = new Cliente();
            cliente.setNombre(faker.name().firstName());
            cliente.setApellido(faker.name().lastName());
            cliente.setFormaPago(faker.currency().name());

            Direccion direccionUno = new Direccion(faker.address().streetName(), Integer.parseInt(faker.address().streetAddressNumber()));
            Direccion direccionDos = new Direccion(faker.address().streetName(), Integer.parseInt(faker.address().streetAddressNumber()));

            cliente.getDirecciones().add(direccionUno);
            cliente.getDirecciones().add(direccionDos);

            entityManager.persist(cliente);

            entityManager.getTransaction().commit();
            System.out.println(cliente);

            entityManager.getTransaction().begin();
            cliente = entityManager.find(Cliente.class, cliente.getId());
            cliente.getDirecciones().remove(direccionUno);
            entityManager.getTransaction().commit();
            System.out.println(cliente);

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }
}
