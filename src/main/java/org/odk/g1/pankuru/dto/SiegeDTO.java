package org.odk.g1.pankuru.dto;

import lombok.Data;
import org.odk.g1.pankuru.Entity.Compagnie.Avion;

@Data
public class SiegeDTO {
    private String numeroSiege;
    private String classeSiege;
    private String positionSiege;
    private double tarif;
    private Avion avion;


}
