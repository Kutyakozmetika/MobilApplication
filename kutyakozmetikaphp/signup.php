<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['nev']) && isset($_POST['cim'])&& isset($_POST['email']) &&  isset($_POST['telefonszam']) && isset($_POST['felhasznalonev']) && isset($_POST['jelszo'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("felhasznalo", $_POST['nev'], $_POST['cim'], $_POST['email'], $_POST['telefonszam'], $_POST['felhasznalonev'], $_POST['jelszo'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>