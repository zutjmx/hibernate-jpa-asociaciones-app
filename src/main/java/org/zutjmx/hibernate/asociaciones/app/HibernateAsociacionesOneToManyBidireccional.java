package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.entity.Factura;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateAsociacionesOneToManyBidireccional {
    public static void main(String[] args) {
        Faker faker = new Faker();
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            Cliente cliente = new Cliente(faker.name().firstName(),faker.name().lastName());
            cliente.setFormaPago(faker.currency().name());

            String nombreFacturaUno = faker.commerce().productName();

            Factura facturaUno = new Factura(nombreFacturaUno,1500L);
            Factura facturaDos = new Factura(faker.commerce().productName(),10000L);

            cliente.addFactura(facturaUno)
                    .addFactura(facturaDos);

            entityManager.persist(cliente);

            entityManager.getTransaction().commit();

            System.out.println(cliente);

            entityManager.getTransaction().begin();
            //Factura facturaAEliminar = entityManager.find(Factura.class,1L);

            Factura facturaAEliminar = new Factura(nombreFacturaUno,1500L);;
            facturaAEliminar.setId(1L);
            cliente.removeFactura(facturaAEliminar);
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
