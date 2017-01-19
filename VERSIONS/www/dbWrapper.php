<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of dbWrapper
 *
 * @author ldulka
 */
class dbWrapper {
    private function connect(){
      $conn = new mysqli($server,$user,$pass,$db);
    }
}
