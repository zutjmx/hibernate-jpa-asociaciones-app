package org.zutjmx.hibernate.asociaciones.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.entity.Factura;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToOneCriteria {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Factura> criteriaQuery = criteriaBuilder.createQuery(Factura.class);
        Root<Factura> facturaRoot = criteriaQuery.from(Factura.class);
        Join<Factura, Cliente> cliente = (Join) facturaRoot.fetch("cliente", JoinType.LEFT);
        cliente.fetch("clienteDetalle",JoinType.LEFT);

        criteriaQuery
                .select(facturaRoot)
                .where(
                        criteriaBuilder.equal(cliente.get("id"),19L)
                );

        List<Factura> facturas = entityManager
                .createQuery(criteriaQuery)
                .getResultList();

        facturas.forEach(
                factura -> System.out.println(
                        factura.getDescripcion() +
                        ", Cliente :: " +
                        factura.getCliente().getNombre()
                )
        );

        entityManager.close();
    }
}
