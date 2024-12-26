package org.odk.g1.pankuru.Service.Interface;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    T ajout(T entity);
    List<T> liste();
    Optional<T> trouverParId(ID id);
    T misAJour(T entity, ID Id);
    void supprimer(ID id);
}