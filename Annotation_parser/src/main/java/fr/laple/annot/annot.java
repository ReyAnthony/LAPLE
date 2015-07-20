package fr.laple.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zaafranigabriel on 25/06/2015.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Annot {
    String title();
    String nom();
    String observation();
}
