package com.astrazeneca;

import org.junit.Test;


public class LikeSongSinatra extends LikeSinatraParent {
    @Test
    public void LikeSinatra()
    {

        navegarSitio("https://evening-bastion-49392.herokuapp.com/");
        validarHomePage();
        navegarSongsPage("https://evening-bastion-49392.herokuapp.com/songs");
        navegarPrimerCancion();
        validarLikeAgregado();
        cerrarBrowser();


    }


}