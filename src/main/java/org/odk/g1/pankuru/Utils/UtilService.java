package org.odk.g1.pankuru.Utils;

import org.odk.g1.pankuru.dto.RolePermissionDTO;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UtilService {

    public static boolean isValidEmail(String email) {
        final String EMAIL_PATTERN =
                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static Map<String, String[]> extractPermissions(List<RolePermissionDTO> rolePermissionDTOList) {
        // Création d'une Map pour stocker les rôles associés aux endpoints et permissions
        Map<String, Set<String>> permissionsMap = new HashMap<>();

        for (RolePermissionDTO dto : rolePermissionDTOList) {
            String key = "/" + dto.getPermissionEndpoint() + "/" + dto.getPermissionPermission().toString().toLowerCase();
            permissionsMap
                    .computeIfAbsent(key, k -> new HashSet<>())
                    .add(dto.getRoleName());
        }

        // Transformation de la Map pour que les rôles soient sous forme de tableau
        return permissionsMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().toArray(new String[0])
                ));
    }

    public static boolean isValidPhone(String phone) {
        final String PHONE_PATTERN = "^[+]?[0-9]{10,13}$";
        final Pattern pattern = Pattern.compile(PHONE_PATTERN);
        if (phone == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isValidPassportNumber(String passportNumber) {
        final String PASSPORT_PATTERN = "^[A-Z]{1,2}[0-9]{6}$";
        final Pattern pattern = Pattern.compile(PASSPORT_PATTERN);
        if (passportNumber == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(passportNumber);
        return matcher.matches();
    }

    
    public static boolean isValidVisaNumber(String visaNumber) {
        final String VISA_PATTERN = "^[A-Z]{1,2}[0-9]{6}$";
        final Pattern pattern = Pattern.compile(VISA_PATTERN);
        if (visaNumber == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(visaNumber);
        return matcher.matches();
    }

    
    public static boolean isValidCarteBancaireNumber(String carteBancaireNumber) {
        final String CARTE_BANCAIRE_PATTERN = "^[0-9]{16}$";
        final Pattern pattern = Pattern.compile(CARTE_BANCAIRE_PATTERN);
        if (carteBancaireNumber == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(carteBancaireNumber);
        return matcher.matches();
    }


    public static boolean isValidPassword(String password) {
        return password != null && password.length() > 6;
    }
    
    public static String generateRandomPseudo() {
        String[] pseudoList = {"Nyetim", "Bravo", "Kpous", "DELTA", "Kpou", "Kpoum", "Kpouma", "Kpoumata", "Kpassima"};
        Random random = new Random();
        int randomIndex = random.nextInt(pseudoList.length);
        return pseudoList[randomIndex];
    }

    
}
