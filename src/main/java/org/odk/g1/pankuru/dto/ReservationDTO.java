package org.odk.g1.pankuru.dto;

import lombok.Data;

@Data
public class ReservationDTO {
    private String villeDeDepart;
    private String villeArrivee;
    private int nbreDePassager;
    private int dateDeDepart;

}
