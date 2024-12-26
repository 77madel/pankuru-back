package org.odk.g1.pankuru.Service.Interface;

public interface ReservationService {
    void faireUneReservation();
    boolean modifierSaReservation(Long id);
    boolean annulerSaReservation(Long id);
    boolean voirSesReservation();
}
